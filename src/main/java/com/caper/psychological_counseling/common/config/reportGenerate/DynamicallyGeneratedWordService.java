package com.caper.psychological_counseling.common.config.reportGenerate;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.model.domain.*;
import com.caper.psychological_counseling.model.vo.ConsultRecordVO;
import com.caper.psychological_counseling.model.vo.ReportRecord;
import com.caper.psychological_counseling.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.cache.URLTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import javax.annotation.Resource;

@Service
public class DynamicallyGeneratedWordService {
	private static Configuration freemarkerConfig;

	@Resource
	private SysUserService sysUserService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private ConsultService consultService;
	@Resource
	private ConsultRecordService consultRecordService;
	@Resource
	private ApplicationService applicationService;

	static {
		freemarkerConfig = new Configuration(Configuration.VERSION_2_3_22);
		freemarkerConfig.setEncoding(Locale.getDefault(), "UTF-8");
	}
	
	/**
	 * 生成word文档
	 */
	public void genWordFile(Long user_id, Long consult_id) {

		//判断咨询是否结束
		Consult consult = consultService.getById(consult_id);

		if (!consult.getStatus().equals(1) && !consult.getStatus().equals(2)) {
			throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "该咨询未结束，不能下载生成结案报告");
		}


		String filePath = "/home/user/conseling/reports/" + consult_id + ".doc";
//		String filePath = "D://" + consult_id + ".doc";

		//查看文件是否存在 若存在则直接读取
		File file = new File(filePath);
		if (file.exists()) {
			//直接获取

			return;
		}

		//如果文件不存在则按照模板新建
		SysUser user = sysUserService.getById(user_id);

		Map<String,Object> result = new HashMap<String,Object>();
		result.put("name", user.getName());
		result.put("sex", user.getGender());
		result.put("age", "20");
		result.put("username", user.getUsername());
		result.put("org_id", organizationService.getById(user.getOrgId()).getOrgName());
		result.put("telephone", user.getTelephone());
		result.put("type", consult.getType());
		result.put("self_talk", applicationService.getById(consult.getApplicationId()).getDescription());

		List<ReportRecord> reportRecords = consultRecordService.selectReportByConsultId(consult_id);


		result.put("times",reportRecords.size());
		result.put("begin_time", reportRecords.get(0).getTime().split(" ")[0]);
		result.put("end_time", reportRecords.get(reportRecords.size() - 1).getTime().split(" ")[0]);

		result.put("recordList", reportRecords);

		result.put("result", consult.getResult());
		result.put("evaluate", consult.getEvaluate());
		result.put("consultor", consultRecordService.getConsultorNameByConsultId(consult.getId()).get(0));

		String strDateFormat = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

		result.put("report_time", sdf.format(new Date()));
		result.put("print_time", sdf.format(new Date()));

		
		freemarkerConfig.setTemplateLoader(new URLTemplateLoader() {
			
			@Override
			protected URL getURL(String arg0) {
				return DynamicallyGeneratedWordService.class.getResource("/report.xml");//此处需要注意test.xml模板的路径,不要搞错了，否则获取不到模板，我是放在src/main/java目录下
			}
		});

		try {
			Template temp = freemarkerConfig.getTemplate("report.xml");

			File targetFile = new File(filePath);
			Writer out = new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8");

			//执行模板替换
			temp.process(result, out);
			out.flush();
			out.close();
			System.gc();
		}catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "系统出现异常");
		}
	}
}


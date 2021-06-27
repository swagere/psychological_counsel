package com.caper.psychological_counseling.common.config.reportGenerate;
 
import java.io.IOException;
import java.util.List;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class DynamicallyGeneratedWordController {
	@Autowired
	private DynamicallyGeneratedWordService dynamicallyGeneratedWordService;

	@RequestMapping("/report/consult/{consult_id}/user/{user_id}")
	public AjaxResponse generateReport(@PathVariable("user_id") Long user_id, @PathVariable("consult_id") Long consult_id){
		dynamicallyGeneratedWordService.genWordFile(user_id, consult_id);
		return AjaxResponse.success();
	}
}
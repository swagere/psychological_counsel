package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.ConsultRecordVO;

import java.util.List;

import com.caper.psychological_counseling.model.vo.ReportRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsultRecordService extends IService<ConsultRecord> {

    //建立咨询记录
    void build_consultRecord(ConsultRecord consultRecord);

    //用户评价咨询记录
    void update_evaluate(String evaluate,Long id);

    List<ConsultRecordVO> selectByConsultId(Long consult_id);

    List<ConsultRecordVO> selectByOrgIdAndChecked(Long org_id);

    List<ConsultRecordVO> getByDateAndChecked(List<Long> schedules1);

    boolean updateScheduleIdById(Long schedule_id, Long consultRecord_id);

    boolean updateCheck(Long assistant_id, Long consultRecord_id);

    List<ConsultRecordVO> getByUserId(Long user_id);


    //添加咨询记录表状态
    void update_consultRecordStatus(Integer status,Long id);


    //修改咨询记录结论
    void update_consultRecordResult(String result,Long id);



    //查看咨询记录表（根据consultId）
    List<ConsultRecord> selectConsultRecord(Long id);

    //查看咨询记录表times（根据Id）
    Integer selectConsultRecordTimes(Long id);

    //查看咨询记录表的咨询表id（根据Id）
    Long selectConsultId(Long id);


    List<ReportRecord> selectReportByConsultId(Long consult_id);

    List<String> getConsultorNameByConsultId(Long id);
}

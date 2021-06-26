package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.ConsultRecordVO;

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
}

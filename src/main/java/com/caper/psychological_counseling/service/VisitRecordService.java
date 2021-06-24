package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.VisitRecordVO;


import java.util.List;

public interface VisitRecordService extends IService<VisitRecord> {

    void insert_visitRecord (VisitRecord visitRecord);

    List<VisitRecord> selectByID(Long id);

    List<VisitRecordVO> getByDateAndChecked(List<Long> schedules1);

    List<VisitRecordVO> selectByOrgId(Long org_id);

    boolean updateScheduleIdById(Long schedule_id, Long visitRecord_id);

    boolean updateCheck(Long system_id, Long visitRecord_id);

    List<VisitRecordVO> getByOrgIdAndUserId(Long org_id, Long user_id);
}

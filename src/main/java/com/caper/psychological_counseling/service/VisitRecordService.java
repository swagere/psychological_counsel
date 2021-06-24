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
}

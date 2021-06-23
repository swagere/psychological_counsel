package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.baomidou.mybatisplus.extension.service.IService;

public interface VisitRecordService extends IService<VisitRecord> {

    void insert_visitRecord (VisitRecord visitRecord);

    VisitRecord selectByID(Long id);
}

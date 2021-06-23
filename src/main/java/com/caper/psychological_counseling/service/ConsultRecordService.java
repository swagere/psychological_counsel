package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ConsultRecordService extends IService<ConsultRecord> {

    //建立咨询记录
    void build_consultRecord(ConsultRecord consultRecord);

}

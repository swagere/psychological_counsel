package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.caper.psychological_counseling.mapper.VisitRecordMapper;
import com.caper.psychological_counseling.service.VisitRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VisitRecordServiceImpl extends ServiceImpl<VisitRecordMapper, VisitRecord> implements VisitRecordService {

    @Resource
    VisitRecordMapper visitRecordMapper;

    @Override
    public void insert_visitRecord (VisitRecord visitRecord){

        visitRecordMapper.insert(visitRecord);

    }
}

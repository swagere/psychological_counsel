package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.caper.psychological_counseling.mapper.ConsultRecordMapper;
import com.caper.psychological_counseling.service.ConsultRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ConsultRecordServiceImpl extends ServiceImpl<ConsultRecordMapper, ConsultRecord> implements ConsultRecordService {

    @Resource
    ConsultRecordMapper consultRecordMapper;


    @Override
    public void build_consultRecord(ConsultRecord consultRecord){

        consultRecordMapper.insert(consultRecord);
    }

    //用户评价咨询
    @Override
    public void update_evaluate(String evaluate,Long id){
        consultRecordMapper.update_evaluate(evaluate, id);
    }
}

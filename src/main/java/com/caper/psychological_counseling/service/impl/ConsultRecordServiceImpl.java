package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.caper.psychological_counseling.mapper.ConsultRecordMapper;
import com.caper.psychological_counseling.service.ConsultRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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


    //添加咨询记录表状态
    @Override
    public void update_consultRecordStatus(Integer status,Long id){
        consultRecordMapper.update_consultRecordStatus(status, id);
    }


    //修改咨询记录结论
    @Override
    public void update_consultRecordResult(String result,Long id){
        consultRecordMapper.update_consultRecordResult(result, id);
    }



    //查看咨询记录表（根据consultId）
    @Override
    public List<ConsultRecord> selectConsultRecord(Long id){
        return consultRecordMapper.selectConsultRecord(id);
    }
}

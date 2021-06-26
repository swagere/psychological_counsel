package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.caper.psychological_counseling.mapper.ConsultRecordMapper;
import com.caper.psychological_counseling.model.vo.ConsultRecordVO;
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

    @Override
    public List<ConsultRecordVO> selectByConsultId(Long consult_id) {
        return consultRecordMapper.selectByConsultId(consult_id);
    }

    @Override
    public List<ConsultRecordVO> selectByOrgIdAndChecked(Long org_id) {
        return consultRecordMapper.selectByOrgIdAndChecked(org_id);
    }

    @Override
    public List<ConsultRecordVO> getByDateAndChecked(List<Long> schedules1) {
        return consultRecordMapper.selectByDateAndChecked(schedules1);
    }

    @Override
    public boolean updateScheduleIdById(Long schedule_id, Long consultRecord_id) {
        return consultRecordMapper.updateScheduleIdById(schedule_id, consultRecord_id);
    }

    @Override
    public boolean updateCheck(Long assistant_id, Long consultRecord_id) {
        return consultRecordMapper.updateCheck(assistant_id, consultRecord_id);
    }

    @Override
    public List<ConsultRecordVO> getByUserId(Long user_id) {
        return consultRecordMapper.selectByUserId(user_id);
    }
}

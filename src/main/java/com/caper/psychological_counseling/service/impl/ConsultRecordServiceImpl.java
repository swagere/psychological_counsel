package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.caper.psychological_counseling.mapper.ConsultRecordMapper;
import com.caper.psychological_counseling.model.vo.ConsultRecordVO;
import com.caper.psychological_counseling.service.ConsultRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
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

    //查看咨询记录表（根据consultId）
    @Override
    public Integer selectConsultRecordTimes(Long id){
        return consultRecordMapper.selectConsultRecordTimes(id);
    }

    //查看咨询记录表的咨询表id（根据Id）
    @Override
    public Long selectConsultId(Long id){
        return consultRecordMapper.selectConsultId(id);
    }
}

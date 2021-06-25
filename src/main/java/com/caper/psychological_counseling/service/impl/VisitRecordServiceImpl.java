package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.caper.psychological_counseling.mapper.VisitRecordMapper;
import com.caper.psychological_counseling.model.vo.VisitRecordVO;
import com.caper.psychological_counseling.model.vo.ScheduleVO;
import com.caper.psychological_counseling.model.vo.VisitRecordScheduleVO;
import com.caper.psychological_counseling.service.VisitRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class VisitRecordServiceImpl extends ServiceImpl<VisitRecordMapper, VisitRecord> implements VisitRecordService {

    @Resource
    VisitRecordMapper visitRecordMapper;

    @Override
    public void insert_visitRecord (VisitRecord visitRecord){

        visitRecordMapper.insert(visitRecord);

    }

    /**
     * 根据schedule_ids选择还未审核的
     * @param schedules1
     * @return
     */
    @Override
    public List<VisitRecordVO> getByDateAndChecked(List<Long> schedules1) {
        return visitRecordMapper.selectByDateAndChecked(schedules1);
    }

    @Override
    public List<VisitRecordVO> selectByOrgId(Long org_id) {
        return visitRecordMapper.selectByOrgId(org_id);
    }

    @Override
    public boolean updateScheduleIdById(Long schedule_id, Long visitRecord_id) {
        return visitRecordMapper.updateScheduleIdById(schedule_id, visitRecord_id);
    }

    @Override
    public boolean updateCheck(Long system_id, Long visitRecord_id) {
        return visitRecordMapper.updateCheck(system_id, visitRecord_id);
    }

    @Override
    public List<VisitRecordVO> getByOrgIdAndUserId(Long org_id, Long user_id) {
        return visitRecordMapper.selectByOrgIdAndUserId(org_id, user_id);
    }

    //用户查询自己的初访记录表
    @Override
    public List<VisitRecord> selectByID(Long id){

        return visitRecordMapper.selectByID(id);
    }


    //初访员查询自己的初访记录表
    @Override
    public List<VisitRecord> selectVisitor_Records(Long id){
        Date date = new Date(new java.util.Date().getTime());
        return visitRecordMapper.selectVisitorRecords(id, date);
    }




    //初访员查询自己的排班表
    @Override
    public List<ScheduleVO> selectVisitor_Schedules(Long id){
        Date date = new Date(new java.util.Date().getTime());
        return visitRecordMapper.selectVisitorSchedules(id, date);
    }


    //初访员更新初访结果
    @Override
    public void update_result(Integer rank,String type,Integer status,Integer diag,String result,Long id){

        visitRecordMapper.update_visitRecord(rank, type, status, diag, result, id);
    }
}

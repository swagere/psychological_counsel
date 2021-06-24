package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.caper.psychological_counseling.mapper.VisitRecordMapper;
import com.caper.psychological_counseling.model.vo.VisitRecordVO;
import com.caper.psychological_counseling.service.VisitRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public List<VisitRecord> selectByID(Long id){

        return visitRecordMapper.selectByID(id);
    }
}

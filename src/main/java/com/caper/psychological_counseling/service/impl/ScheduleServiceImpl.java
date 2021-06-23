package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.mapper.ScheduleMapper;
import com.caper.psychological_counseling.model.dto.ScheduleDTO;
import com.caper.psychological_counseling.service.ScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * author: meidou
 */

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    @Resource
    ScheduleMapper scheduleMapper;


    //查询
    @Override
    public List<ScheduleDTO> getSchedule(Long area_id,String type){
        Date date = new Date(new java.util.Date().getTime());

        List<ScheduleDTO> scheduleDTO= scheduleMapper.find_schedule(area_id, type,date);
        return scheduleDTO;
    }
}

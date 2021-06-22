package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.mapper.ScheduleMapper;
import com.caper.psychological_counseling.service.ScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Schedule getSchedule(){
        Schedule schedule = new Schedule();
        //PageHelper.startPage(1,10);



        return schedule;
    }

    /**
     * 生成两周的实际排班表
     */
    @Override
    public void generateTwoWeekSchedule(Integer week, Integer year, Integer month, Integer day) {
        //创建最近两周
        for (int i = 1; i <= 14; i++) {
            week = week + i;
            day = day + i;

        }
    }
}

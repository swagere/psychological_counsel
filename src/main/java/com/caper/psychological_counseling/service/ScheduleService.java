package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

public interface ScheduleService extends IService<Schedule> {

    //查询
    Schedule getSchedule();

    void generateTwoWeekSchedule(Date begin_time, Date end_time);

    long getDateGap(Date begin_time, Date end_time);
}

package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

public interface ScheduleService extends IService<Schedule> {

    //查询
    Schedule getSchedule();

    void generateWeekSchedule(Date begin_time, Date end_time, List<Long> area_ids, List<Long> user_ids);

    long getDateGap(Date begin_time, Date end_time);
}

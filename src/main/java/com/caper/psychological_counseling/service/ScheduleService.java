package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.dto.ScheduleDTO;
import com.caper.psychological_counseling.model.dto.SingleScheduleDTO;

import java.util.List;

import java.util.Date;
import java.util.List;

public interface ScheduleService extends IService<Schedule> {

    //查询
    List<ScheduleDTO> getSchedule(Long area_id,String type);

    void generateWeekSchedule(Date begin_time, Date end_time, List<Long> area_ids, List<Long> user_ids);

    long getDateGap(Date begin_time, Date end_time);

    void saveSchedule(Schedule schedule);

    List<Long> selectByAreaIdAndDate(Long area_id, Date date);
}

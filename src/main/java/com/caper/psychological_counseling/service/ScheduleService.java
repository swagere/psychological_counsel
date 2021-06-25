package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.ScheduleVO;

import java.util.List;

import java.util.Date;

public interface ScheduleService extends IService<Schedule> {

    //查询
    List<ScheduleVO> getSchedule(Long area_id, String type);

    void generateWeekSchedule(Date begin_time, Date end_time, List<Long> area_ids, List<Long> user_ids);

    long getDateGap(Date begin_time, Date end_time);

    void saveSchedule(Schedule schedule);

    List<Long> selectByAreaIdAndDate(Long area_id, Date date);

    List<Long> selectByDateAndOrgId(Long org_id, Date date);

    List<Long> selectByOrgIdBetweenDate(Long org_id, Date begin, Date end);

    List<Long> selectByDateAndOccupiedAndOrgId(Long org_id, Date time);

    boolean updateOccupiedById(Long schedule_id);
}

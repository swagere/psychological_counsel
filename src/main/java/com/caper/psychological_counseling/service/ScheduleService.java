package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kve
 * @since 2021-06-18
 */

/**
 * author: meidou
 * @since 2021-06-19
 */
public interface ScheduleService extends IService<Schedule> {

    //查询
    Schedule getSchedule();

    void generateTwoWeekSchedule(Integer week, Integer year, Integer month, Integer day);
}

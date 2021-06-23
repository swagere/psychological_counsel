package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.dto.ScheduleDTO;

import java.util.List;

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
    List<ScheduleDTO> getSchedule(Long area_id,String type);

}

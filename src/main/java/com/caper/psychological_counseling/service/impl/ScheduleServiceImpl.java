package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.mapper.ScheduleMapper;
import com.caper.psychological_counseling.service.ScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

}

package com.caper.psychological_counseling.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.mapper.ScheduleMapper;
import com.caper.psychological_counseling.mapper.SystemCommonMapper;
import com.caper.psychological_counseling.model.domain.CommonSchedule;
import com.caper.psychological_counseling.mapper.CommonScheduleMapper;
import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.service.CommonScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kve
 * @since 2021-06-18
 */
@Service
public class CommonScheduleServiceImpl extends ServiceImpl<CommonScheduleMapper, CommonSchedule> implements CommonScheduleService {

    @Resource
    private SystemCommonMapper systemCommonMapper;

    @Resource
    private ScheduleMapper scheduleMapper;

}

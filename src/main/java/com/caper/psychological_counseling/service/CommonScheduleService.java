package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.CommonSchedule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface CommonScheduleService extends IService<CommonSchedule> {
    Map getCommonScheduleByRoleId(Long role_id);
}

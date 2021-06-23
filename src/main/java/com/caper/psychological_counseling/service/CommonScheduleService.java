package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.CommonSchedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.dto.UserIdAndAreaIdAndWeek;
import com.caper.psychological_counseling.model.dto.UserIdAndAreaIds;
import com.caper.psychological_counseling.model.vo.CommonScheduleVO;

import java.util.List;
import java.util.Map;

public interface CommonScheduleService extends IService<CommonSchedule> {

    void saveCommonSchedule(CommonSchedule commonSchedule);

    List<CommonScheduleVO> getByUserIdsAndAreaIds(UserIdAndAreaIds ids);

    List<CommonScheduleVO> getByUserIdsAndAreaIdAndWeek(UserIdAndAreaIdAndWeek ids);
}

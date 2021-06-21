package com.caper.psychological_counseling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.domain.CommonSchedule;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonScheduleMapper extends BaseMapper<CommonSchedule> {
    @Override
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(CommonSchedule entity);
}

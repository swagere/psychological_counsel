package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleMapper extends BaseMapper<Schedule> {

    @Override
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Schedule entity);
}

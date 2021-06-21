package com.caper.psychological_counseling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.domain.CommonSchedule;
import com.caper.psychological_counseling.model.dto.UserIdAndAreaIds;
import com.caper.psychological_counseling.model.vo.CommonScheduleVO;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonScheduleMapper extends BaseMapper<CommonSchedule> {
    @Override
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(CommonSchedule entity);

    List<CommonScheduleVO> selectByUserIdsAndAreaIds(@Param("ids")UserIdAndAreaIds ids);
}

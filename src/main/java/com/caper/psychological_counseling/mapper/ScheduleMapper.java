package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleMapper extends BaseMapper<Schedule> {

    @Select("SELECT u.name,c.week,c.date,c.begin_time,c.end_time,a.area_name\n" +
            "FROM sys_user u \n" +
            "INNER JOIN schedule c ON(u.id = c.user_id)\n" +
            "INNER JOIN area a ON(c.area_id = a.id)\n" +
            "WHERE u.type like '%${type}%' and a.org_id = #{area_id}")
    ScheduleDTO find_schedule(@Param("area_id")Long area_id,
                              @Param("type")String type);

}

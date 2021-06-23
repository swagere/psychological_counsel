package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ScheduleMapper extends BaseMapper<Schedule> {

    @Select("SELECT u.name,c.week,c.date,c.begin_time,c.end_time,a.area_name\n" +
            "FROM sys_user u \n" +
            "INNER JOIN schedule c ON(u.id = c.user_id)\n" +
            "INNER JOIN area a ON(c.area_id = a.id)\n" +
            "WHERE u.type like '%${type}%' and a.org_id = #{area_id} and date >= #{date}")
    List<ScheduleDTO> find_schedule(@Param("area_id")Long area_id,
                                    @Param("type")String type,
                                    @Param("date")Date date);

    @Override
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Schedule entity);
}

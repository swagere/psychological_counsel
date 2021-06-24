package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Application;
import com.caper.psychological_counseling.model.domain.Consult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.model.vo.ScheduleVO;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsultMapper extends BaseMapper<Consult> {

    @Select("SELECT u.id,u.name,c.week,c.date,c.begin_time,c.end_time,a.area_name\n" +
            "FROM sys_user u \n" +
            "INNER JOIN schedule c ON(u.id = c.user_id)\n" +
            "INNER JOIN area a ON(c.area_id = a.id)\n" +
            "WHERE u.type like '%${type}%' and a.org_id = #{area_id} and date >= #{date}\n"+
            "and u.id in (SELECT user_id FROM sys_user_role WHERE role_id = 3)")
    List<ScheduleVO> find_consults(@Param("area_id")Long area_id,
                                @Param("type")String type,
                                @Param("date") Date date);

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Consult consult);


}

package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Application;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.dto.ApplicationDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationMapper extends BaseMapper<Application> {

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Application entity);

    @Select("SELECT a.rank,a.type,a.answer,a.grade,a.urgency,a.description\n" +
            "FROM application a\n" +
            "WHERE a.stu_id = #{user_id}")
    ApplicationDTO get_application(@Param("user_id") Long user_id);

}

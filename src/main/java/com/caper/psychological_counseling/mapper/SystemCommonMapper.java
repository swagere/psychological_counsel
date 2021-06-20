package com.caper.psychological_counseling.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RBAC
 * sqlMapper
 */

@Repository
public interface SystemCommonMapper {

    @Select("select user_id " +
            "from sys_user_role " +
            "where role_id = #{role_id}")
    List<Long> getUserIdsByRoleId(@Param("role_id") Long role_id);
}

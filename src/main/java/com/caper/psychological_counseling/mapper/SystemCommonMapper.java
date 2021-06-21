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

    List<Long> getUserIdsByRoleIdAndAreaId(@Param("role_id") Long role_id);
}

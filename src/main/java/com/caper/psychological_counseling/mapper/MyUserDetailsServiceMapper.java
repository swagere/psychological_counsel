package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.common.config.auth.MyUserDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MyUserDetailsServiceMapper {

    //根据userID查询用户信息
    @Select("SELECT username,password,enabled\n" +
            "FROM sys_user u\n" +
            "WHERE u.username = #{userId}")
    MyUserDetails findByUsername(@Param("userId") String userId);

    //根据userID查询用户角色列表
    @Select("SELECT role_code\n" +
            "FROM sys_role r\n" +
            "LEFT JOIN sys_user_role ur ON r.id = ur.role_id\n" +
            "LEFT JOIN sys_user u ON u.id = ur.user_id\n" +
            "WHERE u.username = #{userId}")
    List<String> findRoleByUsername(@Param("userId") String userId);

    //根据用户角色查询用户权限
    @Select({
      "<script>",
         "SELECT url " ,
         "FROM sys_menu m " ,
         "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id " ,
         "LEFT JOIN sys_role r ON r.id = rm.role_id ",
         "WHERE r.role_code IN ",
         "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>",
            "#{roleCode}",
         "</foreach>",
      "</script>"
    })
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);

    //根据用户角色查询用户接口访问权限
    @Select(
            "SELECT url \n" +
                    "FROM sys_api a \n" +
                    "LEFT JOIN sys_role_api ra ON a.id = ra.api_id \n" +
                    "LEFT JOIN sys_role r ON r.id = ra.role_id \n" +
                    "WHERE r.role_code = #{roleCode} \n" +
                    "AND a.status = 0"
    )
    List<String> findApiByRoleCode(@Param("roleCode") String roleCode);

}
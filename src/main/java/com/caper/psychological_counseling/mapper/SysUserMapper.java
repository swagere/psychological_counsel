package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT name,telephone,email,gender,description,grade\n" +
            "FROM sys_user u\n" +
            "WHERE u.id = #{userId}")
    UserDTO findByUserId(@Param("userId") Long id);

    @Update("UPDATE sys_user SET telephone = #{telephone},email = #{email},gender = #{gender},description = #{description} WHERE id = #{id}")
    void update_name_phone(@Param("id")Long id,
                           @Param("telephone")Long telephone,
                           @Param("email")String email,
                           @Param("gender")Integer gender,
                           @Param("description")String description);

    @Select("select user_id from sys_user_role where role_id = #{role_id}")
    List<Long> selectUserIdsByRoleId(@Param("role_id") Long role_id);

    @Select("select id from sys_user where username = #{username}")
    Long selectIdByUserName(String username);

    @Select("select username from sys_user where id = #{id}")
    String selectUserNameByUserId(Long id);
}

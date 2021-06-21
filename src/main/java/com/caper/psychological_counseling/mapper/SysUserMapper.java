package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT username,telephone,email,gender\n" +
            "FROM sys_user u\n" +
            "WHERE u.id = #{userId}")
    String findByUserId(@Param("userId") Long id);

    @Update("UPDATE sys_user SET telephone = #{telephone},email = #{email},gender = #{gender},description = #{description} WHERE id = #{id}")
    void update_name_phone(@Param("id")Long id,
                           @Param("telephone")Long telephone,
                           @Param("email")String email,
                           @Param("gender")Integer gender,
                           @Param("description")String description);



}

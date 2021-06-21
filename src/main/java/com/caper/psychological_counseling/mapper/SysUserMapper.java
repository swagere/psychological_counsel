package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT username,telephone,email,gender\n" +
            "FROM sys_user u\n" +
            "WHERE u.id = #{userId}")
    String findByUserId(@Param("userId") Long id);

    @Update("UPDATE sys_user SET username = #{name},telephone = #{telephone} WHERE id = #{userId}")
    void update_name_phone(@Param("userId") Long id,@Param("name")String name,@Param("telephone")Long telephone);



}

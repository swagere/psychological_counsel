package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT *\n" +
            "FROM sys_user u\n" +
            "WHERE u.id = #{userId}")
    SysUser findByUserId(@Param("userId") Long id);

}

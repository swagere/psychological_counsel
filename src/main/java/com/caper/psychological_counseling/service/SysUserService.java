package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.dto.UserDTO;

import java.util.List;


public interface SysUserService extends IService<SysUser> {

    //查询
    UserDTO getSysUser(Long id);

    //更新
    void updateSysUser(Long id,Long telephone,String email,Integer gender,String description);

    //删除
    void deleteSysUser(Long id);

    List<Long> getUserIdsByRoleIdAndAreaId(Long role_id);

    Long getUserIdByUserName(String username);
}

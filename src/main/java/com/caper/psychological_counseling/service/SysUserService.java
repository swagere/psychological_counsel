package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * author: meidou
 */


public interface SysUserService extends IService<SysUser> {

    //查询
    SysUser getSysUser(Long id);

    //更新
    void updateSysUser(SysUser sysUser);

    //删除
    void deleteSysUser(Long id);


}

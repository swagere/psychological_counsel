package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface SysUserService extends IService<SysUser> {

    //查询
    String getSysUser(Long id);

    //更新
    void updateSysUser(Long id,String name,Long telephone);

    //删除
    void deleteSysUser(Long id);

    List<Long> getUserIdsByRoleIdAndAreaId(Long role_id);

}

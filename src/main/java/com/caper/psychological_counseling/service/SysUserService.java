package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.dto.UserDTO;
import com.caper.psychological_counseling.model.vo.SysUserVO;

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

    String getUserNameByUserId(Long id);

    Long getRoleIdByUserId(Long id);

    Long getOrgIdByUserId(Long id);

    List<SysUserVO> getByOrgIdAndRoleId(Long org_id, Long role_id);
}

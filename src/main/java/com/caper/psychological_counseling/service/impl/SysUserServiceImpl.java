package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.caper.psychological_counseling.mapper.SysUserMapper;
import com.caper.psychological_counseling.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    protected Mapper dozerMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SystemCommonMapper systemCommonMapper;

    //查询
    @Override
    public String getSysUser(Long id){
        System.out.println(id);
        return sysUserMapper.findByUserId(id);
    }

    //更新
    @Override
    public void updateSysUser(Long id,String name,Long telephone){
        sysUserMapper.update_name_phone(id,name,telephone);

    }

    //删除
    @Override
    public void deleteSysUser(Long id){
        sysUserMapper.deleteById(id);

    }

    /**
     * 根据role_id获取user_id
     */
    @Override
    public List<Long> getUserIdsByRoleId(Long role_id) {
        //按照role_id查出所有user_id [sys_user_role]
        List<Long> userIds = systemCommonMapper.getUserIdsByRoleId(role_id);
        return userIds;
    }

}

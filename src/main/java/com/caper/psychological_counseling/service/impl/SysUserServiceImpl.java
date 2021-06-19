package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.caper.psychological_counseling.mapper.SysUserMapper;
import com.caper.psychological_counseling.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author: meidou
 */




public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    protected Mapper dozerMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    //查询
    @Override
    public SysUser getSysUser(Long id){
        return dozerMapper.map(sysUserMapper.selectById(id),SysUser.class);
    }

    //更新
    @Override
    public void updateSysUser(SysUser sysUser){
        sysUserMapper.updateById(sysUser);

    }

    //删除
    @Override
    public void deleteSysUser(Long id){
        sysUserMapper.deleteById(id);

    }
}

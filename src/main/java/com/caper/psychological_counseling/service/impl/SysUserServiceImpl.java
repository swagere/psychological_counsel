package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.SysUser;
import com.caper.psychological_counseling.mapper.SysUserMapper;
import com.caper.psychological_counseling.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Resource
    private SysUserMapper sysUserMapper;

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

}

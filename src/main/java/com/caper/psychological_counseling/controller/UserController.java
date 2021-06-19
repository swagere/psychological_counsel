package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.model.domain.Application;
import com.caper.psychological_counseling.model.domain.SysUser;
import com.caper.psychological_counseling.service.SysUserService;
import com.caper.psychological_counseling.service.impl.ApplicationServiceImpl;
import com.caper.psychological_counseling.service.impl.SysUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * author: meidou
 */

@Slf4j
@RestController
public class UserController {
    @Autowired
    private SysUserService sysUserService;


    //获取用户信息，根据当前ID获取

    @RequestMapping(value = "/user/{id}", method =  RequestMethod.GET)
    public AjaxResponse getUser(@PathVariable("id")Long id){
        //System.out.println(id);
        SysUser sysUser = sysUserService.getSysUser(id);

        return AjaxResponse.success(sysUser);

    }



    //修改用户信息

    @PutMapping("/user")
    public AjaxResponse updateUser(@RequestBody SysUser sysUser){
        if(sysUser.getId() == null){

            //异常
        }

        SysUserServiceImpl sysUserService = new SysUserServiceImpl();
        sysUserService.updateSysUser(sysUser);

        return AjaxResponse.success();
    }


    //注销账号（删除用户信息）

    @DeleteMapping("/user/{id}")
    public AjaxResponse deleteUser(@PathVariable("id")Long id){

        SysUserServiceImpl sysUserService = new SysUserServiceImpl();
        sysUserService.deleteSysUser(id);

        return AjaxResponse.success();
    }


    //提交初访申请（建立申请表）

    @PostMapping("/submit")
    public AjaxResponse submit_application(@RequestBody Application application){

        //建立申请表
        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.buildApplication(application);


        return AjaxResponse.success();
    }

    //*显示分数、显示所有初访员及其排班时间，用户选初访员时间














    //评价




}

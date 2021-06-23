package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.model.domain.Application;
import com.caper.psychological_counseling.model.domain.SysUser;
import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.caper.psychological_counseling.model.dto.ApplicationDTO;
import com.caper.psychological_counseling.model.dto.ScheduleDTO;
import com.caper.psychological_counseling.model.dto.UserDTO;
import com.caper.psychological_counseling.service.ApplicationService;
import com.caper.psychological_counseling.service.ScheduleService;
import com.caper.psychological_counseling.service.SysUserService;
import com.caper.psychological_counseling.service.VisitRecordService;
import com.caper.psychological_counseling.service.impl.ApplicationServiceImpl;
import com.caper.psychological_counseling.service.impl.SysUserServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * author: meidou
 */

@Slf4j
@RestController
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private VisitRecordService visitRecordService;


    //获取用户信息，根据当前ID获取

    @RequestMapping(value = "/user/getUser/{id}", method =  RequestMethod.GET)
    public AjaxResponse getUser(@PathVariable("id")Long id){
        //System.out.println(id);
        UserDTO userDTO = sysUserService.getSysUser(id);
        System.out.println(userDTO);

        return AjaxResponse.success(userDTO);

    }



    //修改用户信息


    @PutMapping("/user/update")
    public AjaxResponse updateUser(@RequestParam("id") Long id,
                                   @RequestParam("telephone")Long telephone,
                                   @RequestParam("email")String email,
                                   @RequestParam("gender")Integer gender,
                                   @RequestParam("description")String description){

        if(id == null){

            //异常
        }

        System.out.println(id);
        sysUserService.updateSysUser(id,telephone,email,gender,description);

        return AjaxResponse.success();
    }


    //注销账号（删除用户信息）

    @DeleteMapping("/user/{id}")
    public AjaxResponse deleteUser(@PathVariable("id")Long id){

        sysUserService.deleteSysUser(id);
        return AjaxResponse.success();
    }


    //提交初访申请（建立申请表）

    @PostMapping("/user/submit")
    public AjaxResponse submit_application(@RequestBody Application application){

        //建立申请表
        applicationService.buildApplication(application);


        return AjaxResponse.success();
    }


    //查看自己的初访申请表

    @GetMapping("/user/application/{id}")
    public AjaxResponse get_application(@PathVariable("id")Long id){
        ApplicationDTO applicationDTO = applicationService.get_application(id);


        return AjaxResponse.success(applicationDTO);
    }


    //撤销初访申请（删除）暂时不写


    //*显示分数、*根据等级来插队，显示所有初访员及其排班时间，用户选初访员时间
    //提供校区、类型，然后查询出对应的初访员的排班表。
    @PutMapping("/getSchedule")
    public AjaxResponse get_schedule(@RequestParam("area_id")Long id,
                                     @RequestParam("type")String type){

        return  AjaxResponse.success(scheduleService.getSchedule(id, type));
    }


    //选择初访员，同时建立初访记录表
    @PostMapping("/user/SelectVisitor")
    public AjaxResponse selected_visitor(@RequestBody VisitRecord visitRecord){

        visitRecordService.insert_visitRecord(visitRecord);

        return AjaxResponse.success();
    }


    //查看自己的初访记录表
    @GetMapping("/user/getVisitRecord/{id}")
    public  AjaxResponse get_VisitRecord(@PathVariable("id")Long id){
        VisitRecord visitRecord = visitRecordService.selectByID(id);

        System.out.println(visitRecord);
        return AjaxResponse.success(visitRecordService.selectByID(id));
    }


    //推荐咨询师，选择咨询师
    //创建咨询表

    //


















    //评价




}

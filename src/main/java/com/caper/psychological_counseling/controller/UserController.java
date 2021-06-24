package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.model.domain.Application;
import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.caper.psychological_counseling.model.vo.ApplicationVO;
import com.caper.psychological_counseling.model.dto.UserDTO;
import com.caper.psychological_counseling.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private ConsultService consultService;

    @Autowired
    private ConsultRecordService consultRecordService;


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


    //查看自己的初访申请表,根据用户id获取

    @GetMapping("/user/application/{id}")
    public AjaxResponse get_application(@PathVariable("id")Long id){
        List<ApplicationVO> applications = applicationService.get_application(id);


        return AjaxResponse.success(applications);
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
        List<VisitRecord> visitRecords = visitRecordService.selectByID(id);

        //System.out.println(visitRecord);
        return AjaxResponse.success(visitRecords);
    }


    //推荐咨询师，选择咨询师

    @PostMapping("/user/SelectConsultant")
    public AjaxResponse selected_consultant(@RequestParam("area_id")Long id,
                                           @RequestParam("type")String type)
    {
        return AjaxResponse.success(consultService.find_consults(id, type));
    }

    //创建咨询表、8次咨询记录表

    @PostMapping("/user/buildConsult")
    public AjaxResponse build_consult(Consult consult){

        consultService.build_consult(consult);

        for(int i = 0;i < 8;i++){

            //创建8次咨询记录表
            ConsultRecord consultRecord = new ConsultRecord();
            consultRecordService.build_consultRecord(consultRecord);

        }



        return AjaxResponse.success();
    }






















    //评价




}

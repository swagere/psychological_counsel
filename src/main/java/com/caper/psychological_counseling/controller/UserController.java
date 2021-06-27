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

import java.util.ArrayList;
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

    @Autowired
    private QuestionnaireService questionnaireService;



    //提交初访申请（建立申请表）

    @PostMapping("/user/submit")
    public AjaxResponse submit_application(@RequestBody Application application){

        //建立申请表
        applicationService.buildApplication(application);


        return AjaxResponse.success();
    }

    //展示问卷，用户填写问卷
    @GetMapping("/user/showQuestionnaire")
    public AjaxResponse show_questionnaire(){

        return AjaxResponse.success(questionnaireService.show_questionnaire());

    }


    //前端返回answer结果(一个String类型的字符串，0代表选中，1代表未选中)
    //*显示分数、*根据等级来插队
    @PostMapping("/user/getAnswer")
    public AjaxResponse getAnswer(//@RequestParam("answer")String answer,
                                  @RequestParam("id")Long id){
        //分数大于30等级为5，大于25等级为4，大于20等级为3，大于15等级为2，大于0等级为1,
        //只要选了紧急项，等级一律为5
        Long[] anw = new Long[]{(long) 1, (long)2,(long)5,(long)10};
        int grade = 0;
        int urgency = 0;
        int flag = 0;


        for(int i = 1; i <= anw.length; i++){

            grade = grade + questionnaireService.get_grade(anw[i]);
            if(questionnaireService.get_urgent(anw[i]) == 1){
                urgency = 5;
                flag = 1;
            }

        }
        if(flag == 0){

            if(grade >=30 ){
                urgency = 5;
            }
            else if(grade >= 25){
                urgency = 4;
            }
            else if(grade >= 20){
                urgency = 3;
            }
            else if(grade >= 15){
                urgency = 2;
            }
            else {
                urgency = 1;
            }

        }

        applicationService.update_urgency(urgency, id);




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






    //推荐咨询师排班表，选择咨询师

    @PostMapping("/user/SelectConsultant")
    public AjaxResponse selected_consultant(@RequestParam("area_id")Long id,
                                           @RequestParam("type")String type)
    {
        return AjaxResponse.success(consultService.find_consults(id, type));
    }



    //学生评价咨询记录表

    @PutMapping("/user/updateEvaluate")
    public AjaxResponse update_evaluate(@RequestParam("evaluate")String evaluate,
                                        @RequestParam("id")Long id)
    {
        consultService.update_evaluate(evaluate, id);
        return AjaxResponse.success();
    }


    //学生评价咨询表
    @PutMapping("/user/updateRecordEvaluate")
    public AjaxResponse update_Recordevaluate(@RequestParam("evaluate")String evaluate,
                                        @RequestParam("id")Long id)
    {
        consultRecordService.update_evaluate(evaluate, id);
        return AjaxResponse.success();
    }







}

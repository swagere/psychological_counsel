package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.service.ConsultRecordService;
import com.caper.psychological_counseling.service.ConsultService;
import com.caper.psychological_counseling.service.VisitRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ConsultController {

    @Autowired
    ConsultService consultService;

    @Autowired
    ConsultRecordService consultRecordService;

    @Autowired
    VisitRecordService visitRecordService;

    //添加咨询状态

    @PutMapping("/consult/updateConsultStatus")
    public AjaxResponse update_consultStatus(@RequestParam("status")Integer status,
                                             @RequestParam("id")Long id){

        consultService.update_consultStatus(status, id);
        return AjaxResponse.success();
    }

    //添加咨询结论
    @PutMapping("/consult/updateConsultResult")
    public AjaxResponse update_consultResult(@RequestParam("result") String result,
                                             @RequestParam("id") Long id){
        consultService.update_consultResult(result, id);
        return AjaxResponse.success();
    }

    //添加咨询类型
    @PutMapping("/consult/updateConsultType")
    public AjaxResponse update_consultType(@RequestParam("type") String type,
                                           @RequestParam("id") Long id){
        consultService.update_consultType(type, id);
        return AjaxResponse.success();
    }



    //添加咨询记录表状态
    @PutMapping("/consult/updateConsultRecordStatus")
    public AjaxResponse update_consultRecordStatus(@RequestParam("status") Integer status,
                                                   @RequestParam("id") Long id){

        //第一次咨询记录表开始，对应的咨询表状态也要为开始
        if(consultRecordService.selectConsultRecordTimes(id) == 1 && status == 1){
            Long consultID = consultRecordService.selectConsultId(id);
            consultService.update_consultStatus(3, consultID);
        }

        //第八次咨询记录表结束，对应的咨询表状态也要结束
        if(consultRecordService.selectConsultRecordTimes(id) == 8 && status == 1){
            Long consultID = consultRecordService.selectConsultId(id);
            consultService.update_consultStatus(1, consultID);
        }

        consultRecordService.update_consultRecordStatus(status, id);
        return AjaxResponse.success();
    }

    //修改咨询记录结论

    @PutMapping("/consult/updateConsultRecordResult")
    public AjaxResponse update_consultRecordResult(@RequestParam("result")String result,
                                                   @RequestParam("id")Long id){

        consultRecordService.update_consultRecordResult(result, id);
        return AjaxResponse.success();
    }


    //查看咨询表（根据状态）
    @GetMapping("/consult/selectConsult/user/{id}/status/{status}")
    public AjaxResponse selectConsult(@PathVariable("id")Long id,
                                      @PathVariable("status") Integer status){

        //System.out.println(consultService.selectConsult(id, status));
        return AjaxResponse.success(consultService.selectConsult(id, status));
    }

    //查看咨询记录表（根据consult id）
    @GetMapping("/consult/selectConsultRecord/{id}")
    public AjaxResponse selectConsultRecord(@PathVariable("id")Long id){

        return AjaxResponse.success(consultRecordService.selectConsultRecord(id));
    }

    //查看自己的排班表（今天以及以后，今天以及以前，全部）
    @GetMapping("/consult/getVisitSchedulesBefore/{id}")
    public AjaxResponse get_consultSchedulesBefore(@PathVariable("id")Long id){


        return AjaxResponse.success(visitRecordService.selectVisitor_SchedulesBefor(id));
    }


    
    @GetMapping("/consult/getVisitSchedules/{id}")
    public AjaxResponse get_consultSchedules(@PathVariable("id")Long id){

        return AjaxResponse.success(visitRecordService.selectVisitor_Schedules(id));
    }




}

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
    public AjaxResponse update_consultRecordStatus(Integer status,Long id){

        consultRecordService.update_consultRecordStatus(status, id);
        return AjaxResponse.success();
    }

    //修改咨询记录结论

    @PutMapping("/consult/updateConsultRecordResult")
    public AjaxResponse update_consultRecordResult(String result,Long id){

        consultRecordService.update_consultRecordResult(result, id);
        return AjaxResponse.success();
    }


    //查看咨询表（根据状态）
    @GetMapping("/consult/selectConsult")
    public AjaxResponse selectConsult(Long id, Integer status){

        return AjaxResponse.success(consultService.selectConsult(id, status));
    }

    //查看咨询记录表（根据consult id）
    @GetMapping("/consult/selectConsultRecord")
    public AjaxResponse selectConsultRecord(Long id){

        return AjaxResponse.success(consultRecordService.selectConsultRecord(id));
    }

    //查看自己的排班表（今天以及以后，今天以及以前，全部）
    @GetMapping("/consult/getVisitRecordsBefore/{id}")
    public AjaxResponse get_consultRecordsBefore(@PathVariable("id")Long id){


        return AjaxResponse.success(visitRecordService.selectVisitor_RecordsBefor(id));
    }


    
    @GetMapping("/consult/getVisitSchedules/{id}")
    public AjaxResponse get_consultSchedules(@PathVariable("id")Long id){

        return AjaxResponse.success(visitRecordService.selectVisitor_Schedules(id));
    }




}

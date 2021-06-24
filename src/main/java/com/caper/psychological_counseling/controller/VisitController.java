package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.caper.psychological_counseling.service.ConsultRecordService;
import com.caper.psychological_counseling.service.ConsultService;
import com.caper.psychological_counseling.service.VisitRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author:meidou
 */

@Slf4j
@RestController
public class VisitController {

    @Autowired
    private ConsultService consultService;


    @Autowired
    private ConsultRecordService consultRecordService;

    @Autowired
    private VisitRecordService visitRecordService;


    //查看自己被预约的初访记录表
    @GetMapping("/visitor/getVisitRecords/{id}")
    public AjaxResponse get_visitRecords(@PathVariable("id")Long id){


        return AjaxResponse.success();
    }


    //初访结束，新增初访结论
    @PutMapping("/visitor/updateResult")
    public AjaxResponse update_result(Integer rank,String type,Integer status,Integer diag,String result,Long id){

        //id为visitRecord id
        visitRecordService.update_result(rank, type, status, diag, result, id);

        return AjaxResponse.success();
    }






    //创建咨询表、8次咨询记录表

    @PostMapping("/user/buildConsult")
    public AjaxResponse build_consult(){

        Consult consult = new Consult();
        consultService.build_consult(consult);

        for(int i = 0;i < 8;i++){

            //创建8次咨询记录表
            ConsultRecord consultRecord = new ConsultRecord();
            consultRecordService.build_consultRecord(consultRecord);

        }

        return AjaxResponse.success();
    }

}

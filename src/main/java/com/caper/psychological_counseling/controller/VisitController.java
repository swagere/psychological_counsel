package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.caper.psychological_counseling.service.ConsultRecordService;
import com.caper.psychological_counseling.service.ConsultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

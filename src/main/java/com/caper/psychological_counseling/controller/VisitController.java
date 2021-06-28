package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.caper.psychological_counseling.model.dto.ConsultDTO;
import com.caper.psychological_counseling.model.vo.ScheduleVO;
import com.caper.psychological_counseling.service.ConsultRecordService;
import com.caper.psychological_counseling.service.ConsultService;
import com.caper.psychological_counseling.service.VisitRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

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


    //查看自己被预约的初访记录表（根据id和审核状态）
    @PostMapping("/visitor/getVisitRecords")
    public AjaxResponse get_visitRecords(@RequestParam("id")Long id,
                                         @RequestParam("status")Integer status){


        return AjaxResponse.success(visitRecordService.selectVisitor_Records(id,status));
    }

    //查看自己被预约的初访记录表（今天以前）
    @GetMapping("/visitor/getVisitRecordsBefore/{id}")
    public AjaxResponse get_visitRecordsBefore(@PathVariable("id")Long id){


        return AjaxResponse.success(visitRecordService.selectVisitor_RecordsBefor(id));
    }


    //初访员查询自己的排班表（今天以及以后）
    @GetMapping("/visitor/getVisitSchedules/{id}")
    public AjaxResponse get_visitSchedules(@PathVariable("id")Long id){

        return AjaxResponse.success(visitRecordService.selectVisitor_Schedules(id));
    }

    //初访员查询自己的排班表（今天以前）
    @GetMapping("/visitor/getVisitSchedulesBefore/{id}")
    public AjaxResponse get_visitSchedulesBefore(@PathVariable("id")Long id){

        return AjaxResponse.success(visitRecordService.selectVisitor_SchedulesBefor(id));
    }



    //初访结束，新增初访结论
    @PutMapping("/visitor/updateResult")
    public AjaxResponse update_result(Integer rank,String type,Integer status,Integer diag,String result,Long id){

        //id为visitRecord id
        visitRecordService.update_result(rank, type, status, diag, result, id);

        return AjaxResponse.success();
    }

    //查看初访申请表。根据校区
    @GetMapping("/visitor/selectApplication/{org_id}")
    public AjaxResponse selectApplication(@PathVariable("org_id")Long org_id){


        return AjaxResponse.success(visitRecordService.select_Applications(org_id));
    }





    //创建咨询表、8次咨询记录表

    @PostMapping("/visitor/buildConsult")
    public AjaxResponse build_consult(@RequestBody ConsultDTO consultDTO){

        Consult consult = new Consult();

        //设置默认值和申请表id
        consult.setApplicationId(consultDTO.getApplicationId());
        consult.setStatus(4);
        consult.setDeleted(0);

        //建立咨询表
        consultService.build_consult(consult);



        //找出schedule对应的日期
        Long scheduleId = consultDTO.getScheduleId();
        ScheduleVO find = consultService.find_schedule(consultDTO.getScheduleId());



        for(int i = 1;i <= 8;i++){
            //创建8次咨询记录表
            //对应的排班表的is_occupied +1
            if(i != 1){


                //日期+7天
                Date date = find.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                Date end_time = calendar.getTime();
                find.setDate(end_time);

                //设置要查询的日期
                Date date1 = find.getDate();
                String begin_time = find.getBeginTime();
                String end_time1 = find.getEndTime();
                Long user_id = find.getTeaId();
                Long area_id = find.getAreaId();

                //查询+7天后的scheduleId
                scheduleId = consultService.find_scheduleId(area_id,date1,begin_time,end_time1,user_id);
            }
            ConsultRecord consultRecord = new ConsultRecord();
            consultRecord.setConsultId(consult.getId());
            consultRecord.setStuId(consultDTO.getStuId());
            consultRecord.setScheduleId(scheduleId);
            consultRecord.setDeleted(0);
            consultRecord.setChecked(0);
            consultRecord.setTimes(i);
            consultRecordService.build_consultRecord(consultRecord);
            visitRecordService.occupied(scheduleId);

        }


        return AjaxResponse.success();
    }



}

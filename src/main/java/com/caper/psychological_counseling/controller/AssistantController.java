package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.caper.psychological_counseling.model.dto.ConsultRecordToCheckDTO;
import com.caper.psychological_counseling.model.dto.ConsultRecordToScheduleIdDTO;
import com.caper.psychological_counseling.model.dto.VisitRecordToCheckDTO;
import com.caper.psychological_counseling.model.dto.VisitRecordToScheduleIdDTO;
import com.caper.psychological_counseling.model.vo.ConsultRecordVO;
import com.caper.psychological_counseling.model.vo.ConsultVO;
import com.caper.psychological_counseling.service.ConsultRecordService;
import com.caper.psychological_counseling.service.ConsultService;
import com.caper.psychological_counseling.service.ScheduleService;
import com.caper.psychological_counseling.service.VisitRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * author:chou
 *
 * 咨询助理
 */

@Slf4j
@RestController
@RequestMapping(value = "/assistant")
public class AssistantController {
    @Resource
    private ScheduleService scheduleService;

    @Resource
    private VisitRecordService visitRecordService;

    @Resource
    private ConsultService consultService;

    @Resource
    private ConsultRecordService consultRecordService;

    /**
     * 查看所有人的咨询表
     *
     * 输入：校区
     */
    @RequestMapping(value = "/consult/org/{org_id}", method = RequestMethod.GET)
    public AjaxResponse getConsults(@PathVariable("org_id") Long org_id) {
        //schedule_id
        List<Long> schedule_ids = scheduleService.selectByOrgId(org_id);

        //consult
        try {
            //application_id
            List<Long> application_ids = visitRecordService.getApplicationIdsByScheduleIds(schedule_ids);
            List<ConsultVO> consultVOS = consultService.getConsultsByApplicationIds(application_ids);
            return AjaxResponse.success(consultVOS);
        } catch (Exception e) {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"查询失败，校区不正确或不存在"));
        }
    }



    /**
     * 下拉框
     * 通过咨询表查看此人的的咨询记录
     */
    @RequestMapping(value = "/consultRecord/consult/{consult_id}", method = RequestMethod.GET)
    public AjaxResponse getConsultRecord(@PathVariable("consult_id") Long consult_id) {
        List<ConsultRecordVO> consultRecordVOS = consultRecordService.selectByConsultId(consult_id);

        return AjaxResponse.success(consultRecordVOS);
    }



    /**
     * 获取没有审核的申请记录
     *
     * 输入：校区
     */
    @RequestMapping(value = "/noChecked/consultRecord/org/{org_id}", method = RequestMethod.GET)
    public AjaxResponse getWillConsultRecord(@PathVariable("org_id")Long org_id) {
        Map res = new HashMap();
        //--获取紧急申请（今天、明天）+未审核--增加排班--------------------------------------（今天 明天的occupied不能大于1）
        res.put("urgentConsultRecord", consultRecordService.selectByOrgIdAndChecked(org_id));

        //--获取没有审核的申请记录（后天及以后的记录）+未审核--可以更改原排班-----------------
        //1. 获取后天及之后的schedule
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date()); //设置今天的时间
        calendar1.add(Calendar.DAY_OF_MONTH, 2); //设置后天的时间
        List<Long> schedules1 = scheduleService.selectByDateAndOrgId(org_id, calendar1.getTime()); //得到后天及以后的实际排班信息

        //2. 获取vo对象list
        if (!schedules1.isEmpty()) {
            res.put("willConsultRecord", consultRecordService.getByDateAndChecked(schedules1));
        }else {
            res.put("willConsultRecord", new ArrayList<>());
        }


        //获得被多次选择的schedule_id
        //schedule is_occupied > 1
        res.put("conflict_sid", scheduleService.selectByDateAndOccupiedAndOrgId(org_id, calendar1.getTime()));

        //--前端处理-----------------------------------------------------------------------
        //前端获取紧急的记录 匹配schedule_ids与申请表中的schedule_id 以同种颜色展现冲突情况
        return AjaxResponse.success(res);
    }


    /**
     * 补充/修改 排班信息
     * 1. schedule表新增紧急排班
     * 2. schedule_record链向排班
     * 3. 修改schedule is_occupied
     */
    @RequestMapping(value = "/consultRecords/schedule", method = RequestMethod.PUT)
    public AjaxResponse ChangeConsultRecordToScheduleId(@RequestBody ConsultRecordToScheduleIdDTO consultRecordToScheduleIdDTO) {
        Long schedule_id = consultRecordToScheduleIdDTO.getSchedule_id();
        Long consultRecord_id = consultRecordToScheduleIdDTO.getConsultRecord_id();

        //1. visit_record链向排班
        //查询schedule_id是否存在
        try {
            consultRecordService.updateScheduleIdById(schedule_id, consultRecord_id);
        }catch (Exception e){
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"更新失败，排班表/记录表不正确"));
        }

        //2. 修改schedule is_occupied
        try {
            scheduleService.updateOccupiedById(schedule_id);
        }catch (Exception e){
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"更新失败，排班表/记录表不正确"));
        }

        return AjaxResponse.success();
    }

    /**
     * 同意审核
     * 1. is_checked
     * 2. assistant_id
     */
    @RequestMapping(value = "/consultRecords/check", method = RequestMethod.PUT)
    public AjaxResponse ChangeConsultRecordToCheck(@RequestBody ConsultRecordToCheckDTO consultRecordToCheckDTO) {
        Long assistant_id = consultRecordToCheckDTO.getAssistant_id();
        Long consultRecord_id = consultRecordToCheckDTO.getConsultRecord_id();

        try {
            consultRecordService.updateCheck(assistant_id, consultRecord_id);
        }
        catch (Exception e){
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"更新失败，记录表/审核员不正确"));
        }

        return AjaxResponse.success();
    }

    /**
     * 获取自己已经审核的咨询记录
     *
     * 输入：用户id
     */
    @RequestMapping(value = "/consultRecords/user/{user_id}", method = RequestMethod.GET)
    public AjaxResponse getConsultRecordsByUserId(@PathVariable("user_id") Long user_id) {
        return AjaxResponse.success(consultRecordService.getByUserId(user_id));
    }

}

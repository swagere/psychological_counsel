package com.caper.psychological_counseling.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.model.domain.Application;
import com.caper.psychological_counseling.model.domain.Organization;
import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.caper.psychological_counseling.model.dto.VisitRecordToCheckDTO;
import com.caper.psychological_counseling.model.dto.VisitRecordToScheduleIdDTO;
import com.caper.psychological_counseling.model.vo.VisitRecordVO;
import com.caper.psychological_counseling.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * author:chou
 *
 * 中心管理员
 *
 * 初访管理
 * 提示：提前48小时预约
 */

@Slf4j
@RestController
@RequestMapping(("/sys/visit"))
public class SystemVisitController {
    @Resource
    private ScheduleService scheduleService;

    @Resource
    private VisitRecordService visitRecordService;

    @Resource
    private ApplicationService applicationService;

    @Resource
    private AreaService areaService;

    /**
     * 获取没有审核的申请记录
     *
     * 输入：校区
     */
    @RequestMapping(value = "/noChecked/visitRecords/org/{org_id}", method = RequestMethod.GET)
    public AjaxResponse getWillVisitRecords(@PathVariable("org_id")Long org_id) {
        Map res = new HashMap();
        //--获取紧急申请（今天、明天）+未审核--增加排班--------------------------------------（今天 明天的occupied不能大于1）
        res.put("urgentVisitRecord", visitRecordService.selectByOrgIdAndChecked(org_id));

        //--获取没有审核的申请记录（后天及以后的记录）+未审核--可以更改原排班-----------------
        //1. 获取后天及之后的schedule
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date()); //设置今天的时间
        calendar1.add(Calendar.DAY_OF_MONTH, 2); //设置后天的时间
        List<Long> schedules1 = scheduleService.selectByDateAndOrgId(org_id, calendar1.getTime()); //得到后天及以后的实际排班信息

        //2. 获取vo对象list
        if (!schedules1.isEmpty()) {
            res.put("willVisitRecord", visitRecordService.getByDateAndChecked(schedules1));
        }else {
            res.put("willVisitRecord", new ArrayList<>());
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
     * 2. visit_record链向排班
     * 3. 修改schedule is_occupied
     */
    @RequestMapping(value = "/visitRecords/schedule", method = RequestMethod.PUT)
    public AjaxResponse ChangeVisitRecordToScheduleId(@RequestBody VisitRecordToScheduleIdDTO visitRecordToScheduleIdDTO) {
        Long schedule_id = visitRecordToScheduleIdDTO.getSchedule_id();
        Long visitRecord_id = visitRecordToScheduleIdDTO.getVisitRecord_id();

        //1. visit_record链向排班
        //查询schedule_id是否存在
        try {
            visitRecordService.updateScheduleIdById(schedule_id, visitRecord_id);
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
     * 2. system_id
     */
    @RequestMapping(value = "/visitRecords/check", method = RequestMethod.PUT)
    public AjaxResponse ChangeVisitRecordToCheck(@RequestBody VisitRecordToCheckDTO visitRecordToCheckDTO) {
        Long system_id = visitRecordToCheckDTO.getSystem_id();
        Long visitRecord_id = visitRecordToCheckDTO.getVisitRecord_id();

        try {
            visitRecordService.updateCheck(system_id, visitRecord_id);
        }
        catch (Exception e){
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"更新失败，记录表/审核员不正确"));
        }

        return AjaxResponse.success();
    }


    /**
     * 获取自己已经审核的初访记录
     *
     * 输入：用户id
     */
    @RequestMapping(value = "/visitRecords/user/{user_id}", method = RequestMethod.GET)
    public AjaxResponse getVisitRecordsByUserId(@PathVariable("user_id") Long user_id) {
        return AjaxResponse.success(visitRecordService.getByUserId(user_id));
    }

    /**
     * 查看全部初访申请表
     *
     * 输入：校区
     **/
    @RequestMapping(value = "/applications/org/{org_id}", method = RequestMethod.GET)
    public AjaxResponse getApplications(@PathVariable("org_id") Long org_id) {
        //schedule_id
        List<Long> schedule_ids = scheduleService.selectByOrgId(org_id);
        System.out.println(schedule_ids);

        //application_id
        List<Long> application_ids = visitRecordService.getApplicationIdsByScheduleIds(schedule_ids);

        if (application_ids.isEmpty()) {
            return AjaxResponse.error(new CustomException(CustomExceptionType.SYSTEM_ERROR, "该校区没有符合条件的申请表"));
        }

        //application
        List<Application> applications = applicationService.getByIds(application_ids);
        return AjaxResponse.success(applications);
    }

    /**
     * 查看初访记录表
     *
     * 输入：校区
     */
    @RequestMapping(value = "/visitRecords/org/{org_id}")
    public AjaxResponse getVisitRecords(@PathVariable("org_id") Long org_id) {

        List<VisitRecordVO> res = visitRecordService.selectByOrgId(org_id);
        return AjaxResponse.success(res);
    }

}

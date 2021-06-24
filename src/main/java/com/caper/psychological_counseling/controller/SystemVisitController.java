package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.service.ScheduleService;
import com.caper.psychological_counseling.service.VisitRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 获取没有审核的申请记录
     *
     * 输入：校区
     */
    @RequestMapping(value = "/visitRecords/org/{org_id}", method = RequestMethod.GET)
    public AjaxResponse getWillVisitRecords(@PathVariable("org_id")Long org_id) {
        Map res = new HashMap();
        //--获取紧急申请（今天、明天）+未审核--增加排班--------------------------------------（今天 明天的occupied不能大于1）
        res.put("urgentVisitRecord", visitRecordService.selectByOrgId(org_id));

        //--获取没有审核的申请记录（后天及以后的记录）+未审核--可以更改原排班-----------------
        //1. 获取后天及之后的schedule
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date()); //设置今天的时间
        calendar1.add(Calendar.DAY_OF_MONTH, 2); //设置后天的时间
        List<Long> schedules1 = scheduleService.selectByDateAndOrgId(org_id, calendar1.getTime()); //得到后天及以后的实际排班信息

        //2. 获取vo对象list
        res.put("willVisitRecord", visitRecordService.getByDateAndChecked(schedules1));

        //获得被多次选择的schedule_id
        //schedule is_occupied > 1
        res.put("conflict_sid", scheduleService.selectByDateAndOccupiedAndOrgId(org_id, calendar1.getTime()));

        //--前端处理-----------------------------------------------------------------------
        //前端获取不紧急的记录 匹配schedule_ids与申请表中的schedule_id 以同种颜色展现冲突情况
        return AjaxResponse.success(res);
    }

    /**
     * 新增排班
     * 1. schedule表新增紧急排班
     * 2. visit_record链向排班
     */

    /**
     * 修改申请表
     *
     * 解决冲突：
     * 1. 修改visit_record
     * 2. 修改schedule is_occupied
     */
}

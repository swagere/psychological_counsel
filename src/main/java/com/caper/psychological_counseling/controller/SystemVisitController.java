package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.service.ScheduleService;
import com.caper.psychological_counseling.service.VisitRecordService;
import lombok.extern.slf4j.Slf4j;
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
     */
    @RequestMapping(value = "/visitRecords", method = RequestMethod.GET)
    public AjaxResponse getWillVisitRecords() {
        Map res = new HashMap();
        //--获取紧急申请（今天、明天）+未审核--增加排班--------------------------------------
//        List<Long> schedules = ;

        //--获取没有审核的申请记录（后天及以后的记录）+未审核--可以更改原排班-----------------
        //1. 获取后天及之后的schedule
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); //设置今天的时间
        calendar.add(Calendar.DAY_OF_MONTH, 2); //设置后天的时间
        List<Long> schedules1 = scheduleService.selectByDate(calendar.getTime()); //得到后天及以后的实际排班信息

        //2. 获取vo对象list
        res.put("willVisitRecord", visitRecordService.getByDateAndChecked(schedules1));

        //获得被多次选择的schedule_id
        //schedule is_occupied > 1

        //--前端处理-----------------------------------------------------------------------
        //前端获取不紧急的记录 匹配schedule_ids与申请表中的schedule_id 以同种颜色展现冲突情况
        return AjaxResponse.success(res);
    }

    /**
     * 修改申请表
     *
     * 解决冲突：
     * 1. 修改visit_record
     * 2. 修改schedule is_occupied
     */
}

package com.caper.psychological_counseling.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.model.domain.Area;
import com.caper.psychological_counseling.model.domain.CommonSchedule;
import com.caper.psychological_counseling.model.domain.Organization;
import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.model.dto.UserIdAndAreaIds;
import com.caper.psychological_counseling.model.vo.CommonScheduleVO;
import com.caper.psychological_counseling.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * author: chou
 *
 * 中心管理员
 * 排班管理
 */

@Slf4j
@RestController
@RequestMapping(("/sys/schedule"))
public class SystemScheduleController {
    @Autowired
    private CommonScheduleService commonScheduleService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 查询
     * 按角色 校区 返回按周的common排班表
     * @param role_id
     * @return
     *
     * 输入：角色 校区
     * 1. 按照role_id查出所有user_id [sys_user_role]
     * 2. 根据user_id查出所有排班 [common_schedule]
     */
    @RequestMapping(value = "/commonSchedules/org/{org_id}/role/{role_id}", method = RequestMethod.GET)
    public AjaxResponse getWeekSchedules(@PathVariable("org_id") Long org_id, @PathVariable("role_id") Long role_id) {
        //org_id 查出 area_ids
        List<Long> area_ids = areaService.getAreaIdsByOrgId(org_id);

        //按照role_id查出user_ids [sys_user_role] 即这个角色的所有人
        List<Long> user_ids = sysUserService.getUserIdsByRoleIdAndAreaId(role_id);

        //根据user_ids 和 area_ids查出所有排班 [common_schedule]
        UserIdAndAreaIds ids = new UserIdAndAreaIds();
        ids.setArea_ids(area_ids);
        ids.setUser_ids(user_ids);
        List<CommonScheduleVO> commonSchedules = commonScheduleService.getByUserIdsAndAreaIds(ids);
        return AjaxResponse.success(commonSchedules);
    }

    /**
     * 新增一个common排班表项
     * @param commonSchedule
     * @return
     */
    @RequestMapping(value = "/commonSchedules", method = RequestMethod.POST)
    public AjaxResponse addWeekSchedules(@RequestBody CommonSchedule commonSchedule) {
        //判断id是否重复
        CommonSchedule commonSchedule1 = commonScheduleService.getById(commonSchedule.getId());
        if (commonSchedule1 != null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"此处只能添加不能修改");
        }

        //区域 时间和老师的配置
        //在指定的时间内，配置老师和区域
        commonScheduleService.saveCommonSchedule(commonSchedule);

        return AjaxResponse.success();
    }

    /**
     * 新增
     * 定时调用
     * 用于生成以周为单位的实际排班表
     *
     * 暂定与每周日生成下下下周的（即两周之后）
     * @return
     */
    @RequestMapping(value = "/schedules", method = RequestMethod.POST)
    public AjaxResponse ConvertToScheduleByWeek() {
//        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar=Calendar.getInstance();
        Integer week = calendar.get(Calendar.DAY_OF_WEEK) - 1; //星期
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//今天的日期
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+40);//让日期加1  
        System.out.println(calendar.get(Calendar.MONTH));//加1之后的日期Top

        scheduleService.generateTwoWeekSchedule(week, year, month, day);

        //创建下下下周
        for (int i = 1; i <= 7; i++) {

        }

        return AjaxResponse.success();
    }
}

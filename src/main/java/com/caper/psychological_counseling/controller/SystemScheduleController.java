package com.caper.psychological_counseling.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.model.domain.CommonSchedule;
import com.caper.psychological_counseling.service.CommonScheduleService;
import com.caper.psychological_counseling.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询
     * 按角色 返回按周的common排班表
     * @param role_id
     * @return
     *
     * 输出：角色 校区
     * 1. 按照role_id查出所有user_id [sys_user_role]
     * 2. 根据user_id查出所有排班 [common_schedule]
     */
    @RequestMapping(value = "/commonSchedules/{area_id}/{role_id}", method = RequestMethod.GET)
    public AjaxResponse getWeekSchedules(@PathVariable("role_id") Long role_id, @PathVariable("area_id") Long area_id) {
        //按照role_id查出所有user_id [sys_user_role]
        List<Long> userIds = sysUserService.getUserIdsByRoleId(role_id);

        //根据user_id查出所有排班 [common_schedule]
        QueryWrapper<CommonSchedule> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userIds);
        List<CommonSchedule> list = commonScheduleService.list(wrapper);
        return AjaxResponse.success(list);
    }

    /**
     * 增加
     * 新增一个common排班表项
     * @param commonSchedule
     * @return
     */
    @RequestMapping(value = "/commonSchedules", method = RequestMethod.POST)
    public AjaxResponse addWeekSchedules(@RequestBody CommonSchedule commonSchedule) {
        //区域 时间和老师的配置
        System.out.println(commonSchedule);
        return AjaxResponse.success("添加成功");
    }
}

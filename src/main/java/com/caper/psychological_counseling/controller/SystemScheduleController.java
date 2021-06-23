package com.caper.psychological_counseling.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.model.domain.Area;
import com.caper.psychological_counseling.model.domain.CommonSchedule;
import com.caper.psychological_counseling.model.domain.Organization;
import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.model.dto.ScheduleDTO;
import com.caper.psychological_counseling.model.dto.SingleScheduleDTO;
import com.caper.psychological_counseling.model.dto.UserIdAndAreaIds;
import com.caper.psychological_counseling.model.dto.WeekScheduleDTO;
import com.caper.psychological_counseling.model.vo.AreaVO;
import com.caper.psychological_counseling.model.vo.CommonScheduleVO;
import com.caper.psychological_counseling.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

        //判断是否已存在
        QueryWrapper<CommonSchedule> wrapper = new QueryWrapper<>();
        wrapper.eq("week", commonSchedule.getWeek()).eq("user_id", commonSchedule.getUserId()).eq("area_id", commonSchedule.getAreaId()).eq("time_interval", commonSchedule.getTimeInterval());
        CommonSchedule commonSchedule2 = commonScheduleService.getOne(wrapper);
        if (commonSchedule2 != null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"不能重复添加");
        }

        //区域 时间和老师的配置
        //在指定的时间内，配置老师和区域
        commonScheduleService.saveCommonSchedule(commonSchedule);

        return AjaxResponse.success();
    }

    /**
     * 删除一条common排班表项
     * @return
     */
    @RequestMapping(value = "/commonSchedules/{id}", method = RequestMethod.DELETE)
    public AjaxResponse deleteWeekSchedules(@PathVariable("id") Long id) {
        if (id == null) {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR, "id不能为空"));
        }

        boolean flag = commonScheduleService.removeById(id);
        if (flag == false) {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR, "此排班记录不存在或已删除"));
        }

        return AjaxResponse.success();
    }


    /**
     * 新增
     * 定时调用
     * 用于生成以周为单位的实际排班表
     *
     * @return
     */
    @RequestMapping(value = "/weekSchedules", method = RequestMethod.POST)
    public AjaxResponse ConvertToScheduleByWeeks(@RequestBody WeekScheduleDTO scheduleDTO) {
//        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};


        //获得参数
        String begin_time = scheduleDTO.getBegin_time();
        String end_time = scheduleDTO.getEnd_time();
        Long org_id = scheduleDTO.getOrg_id();
        Long role_id = scheduleDTO.getRole_id();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //获得教室id
        List<Long> area_ids = areaService.getAreaIdsByOrgId(org_id);
        List<Long> user_ids = sysUserService.getUserIdsByRoleIdAndAreaId(role_id);

        //创建指定日期的实际排班表
        try {
            scheduleService.generateWeekSchedule(sdf.parse(begin_time), sdf.parse(end_time), area_ids, user_ids);
        }catch (ParseException e) {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"传入时间格式有误"));
        }


        return AjaxResponse.success();
    }

    /**
     * 删除一条实际记录
     */
    @RequestMapping(value = "/singleSchedules/{id}", method = RequestMethod.DELETE)
    public AjaxResponse deleteSchedule(@PathVariable("id") Long id) {
        if (id == null) {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR, "id不能为空"));
        }

        boolean flag = scheduleService.removeById(id);
        if (flag == false) {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR, "此实际排班记录不存在或已删除"));
        }

        return AjaxResponse.success();
    }

    /**
     * 新增实际记录
     *
     * 注意判断是否已经存在（不能重复添加）
     */
    @RequestMapping(value = "/singleSchedules", method = RequestMethod.POST)
    public AjaxResponse addSchedule(@RequestBody SingleScheduleDTO singleScheduleDTO) {
        //判断是否已存在
        QueryWrapper<Schedule> wrapper = new QueryWrapper<>();
        wrapper.eq("week", singleScheduleDTO.getWeek()).eq("date", singleScheduleDTO.getDate()).eq("user_id", singleScheduleDTO.getUserId()).eq("area_id", singleScheduleDTO.getAreaId()).eq("begin_time", singleScheduleDTO.getBeginTime()).eq("end_time", singleScheduleDTO.getEndTime()).eq("is_deleted", 0);
        Schedule schedule2 = scheduleService.getOne(wrapper);
        if (schedule2 != null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"不能重复添加");
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(sdf.parse(singleScheduleDTO.getDate()).getTime());

            Schedule schedule = new Schedule();
            schedule.setWeek(singleScheduleDTO.getWeek());
            schedule.setDate(date);
            schedule.setBeginTime(singleScheduleDTO.getBeginTime());
            schedule.setEndTime(singleScheduleDTO.getEndTime());
            schedule.setAreaId(singleScheduleDTO.getAreaId());
            schedule.setUserId(singleScheduleDTO.getUserId());
            schedule.setDeleted(0);
            schedule.setOccupied(0);

            //区域 时间和老师的配置
            //在指定的时间内，配置老师和区域
            scheduleService.saveSchedule(schedule);
        }catch (ParseException e) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"日期格式有误");
        }

        return AjaxResponse.success();
    }

    /**
     * 查询地址
     *
     * 输入校区
     */
    @RequestMapping(value = "/area/org/{org_id}", method = RequestMethod.GET)
    public AjaxResponse selectAreaByOrgId(@PathVariable("org_id") Long org_id) {
        List<AreaVO> res = areaService.getAreaVOByOrgId(org_id);
        return AjaxResponse.success(res);
    }

    /**
     * 新增地址
     */
    @RequestMapping(value = "/area", method = RequestMethod.POST)
    public AjaxResponse addArea(@RequestBody Area area) {
        //检查是否有相同地址
        QueryWrapper<Area> wrapper = new QueryWrapper<>();
        wrapper.eq("org_id", area.getOrgId()).eq("area_name", area.getAreaName()).eq("status", 0);
        if (!areaService.list(wrapper).isEmpty()) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"不能重复添加");
        }

        //新增地址
        area.setStatus(0);
        areaService.saveArea(area);
        return AjaxResponse.success();
    }

    /**
     * 删除地址
     */
    @RequestMapping(value = "/area/{area_id}", method = RequestMethod.DELETE)
    public AjaxResponse deleteArea(@PathVariable("area_id") Long area_id) {
        //判断是否正在被使用
        //commonSchedule
        QueryWrapper<CommonSchedule> wrapper = new QueryWrapper<>();
        wrapper.eq("area_id", area_id);
        if (!commonScheduleService.list(wrapper).isEmpty()) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"该教室正在被使用，不能直接删除");
        }

        //schedule
        java.util.Date date = new java.util.Date();
        System.out.println(scheduleService.selectByAreaIdAndDate(area_id, date));
        if (!scheduleService.selectByAreaIdAndDate(area_id, date).isEmpty()) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"该教室正在被使用，不能直接删除");
        }

        //删除教室
        areaService.removeById(area_id);
        return AjaxResponse.success();
    }

    /**
     * 更改地址
     */
    @RequestMapping(value = "/area", method = RequestMethod.PUT)
    public AjaxResponse updateArea(@RequestBody Area area) {
        if (area.getId() == null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"请输入id信息");
        }

        if (areaService.updateArea(area)) {
            return AjaxResponse.success();
        }
        return AjaxResponse.success("更新地址信息失败");
    }

}

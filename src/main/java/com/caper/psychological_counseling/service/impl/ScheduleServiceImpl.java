package com.caper.psychological_counseling.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.mapper.CommonScheduleMapper;
import com.caper.psychological_counseling.model.domain.CommonSchedule;
import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.mapper.ScheduleMapper;
import com.caper.psychological_counseling.model.dto.SingleScheduleDTO;
import com.caper.psychological_counseling.model.dto.UserIdAndAreaIdAndWeek;
import com.caper.psychological_counseling.model.dto.UserIdAndAreaIds;
import com.caper.psychological_counseling.model.vo.CommonScheduleVO;
import com.caper.psychological_counseling.service.CommonScheduleService;
import com.caper.psychological_counseling.model.dto.ScheduleDTO;
import com.caper.psychological_counseling.service.ScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    @Resource
    private ScheduleMapper scheduleMapper;

    @Resource
    private CommonScheduleMapper commonScheduleMapper;

    @Autowired
    private CommonScheduleService commonScheduleService;


    //查询
    @Override
    public List<ScheduleDTO> getSchedule(Long area_id,String type){
        Date date = new Date(new java.util.Date().getTime());

        List<ScheduleDTO> scheduleDTO= scheduleMapper.find_schedule(area_id, type,date);
        return scheduleDTO;
    }

    /**
     * 生成实际排班表
     */
    @Override
    public void generateWeekSchedule(Date begin_time, Date end_time, List<Long> area_ids, List<Long> user_ids) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(begin_time); //设置起始日期
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1; //起始日期的星期
        long days = getDateGap(begin_time, end_time);
        if (days <= 0) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"结束时间不能早于开始时间");
        }

        calendar.add(Calendar.DAY_OF_MONTH, -1);

        //创建指定时间范围内
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 1; i <= days; i++) {
            //第i天
            calendar.add(Calendar.DAY_OF_MONTH,1);

            //获取年月日 创建date
            Date date = calendar.getTime();

            //查出week area_ids user_ids为关键词的common schedule 并创建于schedule
            UserIdAndAreaIdAndWeek ids = new UserIdAndAreaIdAndWeek();
            ids.setArea_ids(area_ids);
            ids.setUser_ids(user_ids);
            ids.setWeek(week);
            List<CommonScheduleVO> commonSchedules = commonScheduleService.getByUserIdsAndAreaIdAndWeek(ids);
            week = (week) % 7 + 1;


            String[] begin_times = {"8:00", "9:00","10:00", "11:00", "3:00", "4:00", "5:00"};
            String[] end_times = {"9:00", "10:00","11:00", "12:00", "4:00", "5:00", "6:00"};

            //创建新的schedule
            for (CommonSchedule commonSchedule : commonSchedules) {
                if (commonSchedule.getTimeInterval().equals(1)) {
                    for (int j = 0; j < 4; j++) {
                        Schedule schedule = new Schedule();
                        schedule.setWeek(commonSchedule.getWeek());
                        schedule.setDate(new java.sql.Date(date.getTime()));
                        schedule.setUserId(commonSchedule.getUserId());
                        schedule.setAreaId(commonSchedule.getAreaId());
                        schedule.setOccupied(0);
                        schedule.setBeginTime(begin_times[j]);
                        schedule.setEndTime(end_times[j]);
                        schedule.setDeleted(0);
                        schedules.add(schedule);
                    }
                }
                else {
                    for (int j = 4; j < 7; j++) {
                        Schedule schedule = new Schedule();
                        schedule.setWeek(commonSchedule.getWeek());
                        schedule.setDate(new java.sql.Date(date.getTime()));
                        schedule.setUserId(commonSchedule.getUserId());
                        schedule.setAreaId(commonSchedule.getAreaId());
                        schedule.setOccupied(0);
                        schedule.setBeginTime(begin_times[j]);
                        schedule.setEndTime(end_times[j]);
                        schedule.setDeleted(0);
                        schedules.add(schedule);
                    }
                }

            }
        }

        System.out.println(schedules);
        //保存于数据库
        for (Schedule schedule : schedules) {
            scheduleMapper.insert(schedule);
        }
    }

    /**
     * 获取两个sql.date的相隔天数
     * @return
     */
    @Override
    public long getDateGap(Date begin_time, Date end_time) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date(begin_time.getTime()));
        long begin = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(new Date(end_time.getTime()));
        long end = calendar.get(Calendar.DAY_OF_YEAR);
        return end - begin;
    }

    @Override
    public void saveSchedule(Schedule schedule) {

        scheduleMapper.insert(schedule);
    }

    @Override
    public List<Long> selectByAreaIdAndDate(Long area_id, Date date) {
        return scheduleMapper.selectByAreaIdAndDate(area_id, date);
    }

    @Override
    public List<Long> selectByDate(Date date) {
        return scheduleMapper.selectByDate(date);
    }

}

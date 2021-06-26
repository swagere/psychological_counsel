package com.caper.psychological_counseling.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleVO {

    private Long id;

    private Long userId;

    private String name;

    private Integer week;

    private String date;

    private String beginTime;

    private String endTime;

    private String areaName;





}

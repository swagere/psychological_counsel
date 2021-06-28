package com.caper.psychological_counseling.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ScheduleVO {

    private Long id;

    private Long teaId;

    private String teaName;

    private Integer week;

    private Date date;

    private String beginTime;

    private String endTime;

    private String areaName;

    private Long areaId;




}

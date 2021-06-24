package com.caper.psychological_counseling.model.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleVO {

    private Long id;

    private Long teaId;

    private String teaName;

    private Integer week;

    private String date;

    private String beginTime;

    private String endTime;

    private String areaName;





}

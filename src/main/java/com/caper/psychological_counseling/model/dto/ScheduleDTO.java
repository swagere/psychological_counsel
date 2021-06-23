package com.caper.psychological_counseling.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleDTO {

    private String name;

    private Integer week;

    private String date;

    private String beginTime;

    private String endTime;

    private String areaName;





}

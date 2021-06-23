package com.caper.psychological_counseling.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WeekScheduleDTO {
    private String begin_time;
    private String end_time;
    private Long org_id;
    private Long role_id;
}

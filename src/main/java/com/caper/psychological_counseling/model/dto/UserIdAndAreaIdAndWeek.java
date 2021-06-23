package com.caper.psychological_counseling.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserIdAndAreaIdAndWeek {
    List<Long> user_ids;
    List<Long> area_ids;
    Integer week;
}

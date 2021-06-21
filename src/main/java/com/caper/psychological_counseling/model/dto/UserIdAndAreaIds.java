package com.caper.psychological_counseling.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserIdAndAreaIds {
    List<Long> user_ids;
    List<Long> area_ids;
}

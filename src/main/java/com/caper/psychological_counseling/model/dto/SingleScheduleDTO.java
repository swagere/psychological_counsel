package com.caper.psychological_counseling.model.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class SingleScheduleDTO extends Model<SingleScheduleDTO> {
    private Long id;

    private Integer week;

    private String date;

    private String beginTime;

    private String endTime;

    private Long userId;

    private Long areaId;

}

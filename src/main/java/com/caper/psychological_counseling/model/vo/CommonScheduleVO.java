package com.caper.psychological_counseling.model.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.caper.psychological_counseling.model.domain.CommonSchedule;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
public class CommonScheduleVO extends CommonSchedule {
    private String user_name;
    private String areaName;
}

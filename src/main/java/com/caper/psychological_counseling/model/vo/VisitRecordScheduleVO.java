package com.caper.psychological_counseling.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

@Data
public class VisitRecordScheduleVO extends Model<VisitRecordScheduleVO> {

    private Long id;
    private Long application_id;
    private Long stu_id;
    private String stu_name;
    private Long schedule_id;
    private Integer week;
    private Date date;
    private String begin_time;
    private String end_time;
    private Long tea_id;
    private String tea_name;
    private Long area_id;
    private String area_name;
}

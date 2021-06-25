package com.caper.psychological_counseling.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
public class ConsultRecordVO extends Model<ConsultRecordVO> {

    private Long id;
    private Long stuId;
    private String stuName;
    private Long assistantId;
    private String assistantName;
    private Integer checked;
    private Integer times;
    private Integer status;
    private String result;
    private String evaluate;
    private ScheduleVO scheduleVO;
}

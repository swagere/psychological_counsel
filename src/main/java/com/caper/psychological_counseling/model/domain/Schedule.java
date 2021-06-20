package com.caper.psychological_counseling.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Schedule对象", description="")
public class Schedule extends Model<Schedule> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer week;

    private String date;

    private LocalDate beginTime;

    private LocalDate endTime;

    private Long userId;

    private Long areaId;

    @TableField("is_occupied")
    private Integer occupied;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package com.caper.psychological_counseling.model.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CommonSchedule对象", description="")
public class CommonSchedule extends Model<CommonSchedule> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer week;

    private Long userId;

    private Long areaId;

    private Integer timeInterval;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

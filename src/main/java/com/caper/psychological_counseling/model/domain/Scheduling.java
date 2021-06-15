package com.caper.psychological_counseling.model.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Scheduling对象", description="")
public class Scheduling extends Model<Scheduling> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer week;

    private String date;

    private String workTime;

    private Long userId;

    private Integer isDeleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

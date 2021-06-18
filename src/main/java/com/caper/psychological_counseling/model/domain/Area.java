package com.caper.psychological_counseling.model.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Area对象", description="")
public class Area extends Model<Area> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long orgId;

    private String areaName;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

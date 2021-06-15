package com.caper.psychological_counseling.model.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ConsultRecord对象", description="")
public class ConsultRecord extends Model<ConsultRecord> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long consultorId;

    private Long stuId;

    private Long assistantId;

    private Integer assistantChecked;

    private Integer times;

    private Integer status;

    private String result;

    private LocalDate beginTime;

    private LocalDate endTime;

    private String evaluate;

    private Long recordId;

    private Integer isDeleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

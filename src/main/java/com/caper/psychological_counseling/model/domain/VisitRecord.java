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
@ApiModel(value="VisitRecord对象", description="")
public class VisitRecord extends Model<VisitRecord> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long applicationId;

    private Long stuId;

    private Long systemId;

    private Integer isChecked;

    private Integer rank;

    private Integer status;

    private String type;

    private Integer diag;

    private String result;

    private Long scheduleId;

    private Integer isDeleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package com.caper.psychological_counseling.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Consult对象", description="")
public class Consult extends Model<Consult> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long applicationId;

    private Integer status;

    private String type;

    private String result;

    private String evaluate;

    @TableField("is_deleted")
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

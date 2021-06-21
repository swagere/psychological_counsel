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
@ApiModel(value="Organization对象", description="")
public class Organization extends Model<Organization> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long orgId;

    private String orgIds;

    private String orgName;

    @TableField("is_leaf")
    private Integer leaf;

    private String address;

    private Long telephone;

    private String email;

    private Integer sort;

    private Integer level;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

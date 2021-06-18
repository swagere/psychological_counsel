package com.caper.psychological_counseling.model.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUser对象", description="")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private Integer telephone;

    private String email;

    private Integer gender;

    private LocalDate createTime;

    private String description;

    private Integer grade;

    private Long orgId;

    private Integer enabled;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

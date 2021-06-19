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
@ApiModel(value="SysMenu对象", description="")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String menuName;

    private String url;

    private Long menuPid;

    private String menuPids;

    private Integer isLeaf;

    private String icon;

    private Integer sort;

    private Integer level;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

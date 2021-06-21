package com.caper.psychological_counseling.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Application对象", description="")
public class Application extends Model<Application> {

    private static final long serialVersionUID = 1L;


    private Long id;

    private Long stuId;

    private Integer rank;

    private String type;

    private String answer;

    private Integer grade;

    private Integer urgency;

    private String description;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

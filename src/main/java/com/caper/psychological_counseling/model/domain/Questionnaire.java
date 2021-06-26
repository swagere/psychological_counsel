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
@ApiModel(value="Questionnaire对象", description="")
public class Questionnaire extends Model<Questionnaire> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String question;

    private Integer option;

    private Integer grade;

    private Integer urgent;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

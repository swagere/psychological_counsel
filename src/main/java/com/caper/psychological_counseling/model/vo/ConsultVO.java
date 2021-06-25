package com.caper.psychological_counseling.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class ConsultVO extends Model<ConsultVO> {

    private Long id;

    private Integer status;

    private String type;

    private String result;

    private String evaluate;
}

package com.caper.psychological_counseling.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class ConsultVO {
    private Long id;

    private Long applicationId;

    private Integer status;

    private String type;

    private String result;

    private String evaluate;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;

}

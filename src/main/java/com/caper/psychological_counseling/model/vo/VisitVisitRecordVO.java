package com.caper.psychological_counseling.model.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class VisitVisitRecordVO extends Model<VisitVisitRecordVO> {

    private Long id;

    private Long stuId;

    private String stuName;

}

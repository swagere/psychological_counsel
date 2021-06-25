package com.caper.psychological_counseling.model.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class VisitRecordVO extends Model<VisitRecordVO> {

    //visitRecord
    //schedule
    //application
    private Long id;

    private Long stuId;

    private String stuName;

    private Long systemId;

    private String systemName;

    private ScheduleVO scheduleVO;

    private VisitApplicationVO visitApplicationVO;
}

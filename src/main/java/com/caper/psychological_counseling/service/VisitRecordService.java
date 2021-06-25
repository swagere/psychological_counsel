package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Schedule;
import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.ScheduleVO;
import com.caper.psychological_counseling.model.vo.VisitRecordScheduleVO;


import javax.xml.crypto.Data;
import java.util.List;

public interface VisitRecordService extends IService<VisitRecord> {

    //建立初访记录表
    void insert_visitRecord (VisitRecord visitRecord);

    //用户查询自己的初访记录表
    List<VisitRecord> selectByID(Long id);

    //初访员查询自己的初访记录表
    List<VisitRecord> selectVisitor_Records(Long id);

    //初访员查询自己的排班表
    List<ScheduleVO> selectVisitor_Schedules(Long id);

    //初访员更新初访结果
    void update_result(Integer rank,String type,Integer status,Integer diag,String result,Long id);

    List<VisitRecordScheduleVO> getByDateAndChecked(List<Long> schedules1);
}

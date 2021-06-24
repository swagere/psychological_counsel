package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.VisitRecordScheduleVO;


import java.util.List;

public interface VisitRecordService extends IService<VisitRecord> {

    //建立初访记录表
    void insert_visitRecord (VisitRecord visitRecord);

    //查询
    List<VisitRecord> selectByID(Long id);

    //初访员更新初访结果
    void update_result(Integer rank,String type,Integer status,Integer diag,String result,Long id);

    List<VisitRecordScheduleVO> getByDateAndChecked(List<Long> schedules1);
}

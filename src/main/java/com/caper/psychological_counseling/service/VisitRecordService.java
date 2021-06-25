package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.VisitRecordVO;
import com.caper.psychological_counseling.model.vo.ScheduleVO;


import javax.xml.crypto.Data;
import java.util.List;

public interface VisitRecordService extends IService<VisitRecord> {

    void insert_visitRecord (VisitRecord visitRecord);

    //用户查询自己的初访记录表
    List<VisitRecord> selectByID(Long id);

    List<VisitRecordVO> getByDateAndChecked(List<Long> schedules1);

    List<VisitRecordVO> selectByOrgIdAndChecked(Long org_id);

    boolean updateScheduleIdById(Long schedule_id, Long visitRecord_id);

    boolean updateCheck(Long system_id, Long visitRecord_id);

    List<VisitRecordVO> getByOrgIdAndUserId(Long org_id, Long user_id);
    //初访员查询自己的初访记录表
    List<VisitRecord> selectVisitor_Records(Long id);

    //初访员查询自己的排班表
    List<ScheduleVO> selectVisitor_Schedules(Long id);

    //初访员更新初访结果
    void update_result(Integer rank,String type,Integer status,Integer diag,String result,Long id);

    List<Long> getApplicationIdsByScheduleIds(List<Long> schedule_ids);

    List<VisitRecordVO> selectByOrgId(Long org_id);
}

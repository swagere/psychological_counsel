package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.vo.VisitRecordVO;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.caper.psychological_counseling.model.vo.ScheduleVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface VisitRecordMapper extends BaseMapper<VisitRecord> {


    //新增初访记录
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(VisitRecord visitRecord);

    //用户查看自己的初访记录表
    @Select("SELECT *\n" +
            "FROM visit_record\n" +
            "WHERE id = #{id}")
    List<VisitRecord> selectByID (@Param("id")Long id);



    List<VisitRecordVO> selectByDateAndChecked(@Param("schedules") List<Long> schedules);

    List<VisitRecordVO> selectByOrgIdAndChecked(@Param("org_id") Long org_id);

    boolean updateScheduleIdById(@Param("schedule_id") Long schedule_id, @Param("id") Long visitRecord_id);

    boolean updateCheck(@Param("system_id") Long system_id, @Param("id") Long visitRecord_id);

    List<VisitRecordVO> selectByOrgIdAndUserId(@Param("org_id")Long org_id, @Param("system_id")Long system_id);

    //初访师查看自己的初访记录表（今天以及以后）
    @Select("SELECT *\n" +
            "FROM visit_record\n" +
            "WHERE schedule_id in(SELECT id FROM `schedule` WHERE user_id = #{id} and date >= #{date} )")
    List<VisitRecord> selectVisitorRecords(@Param("id")Long id,
                                          @Param("date") Date date);




    //初访师查看自己的排班时间（今天以及以后）
    @Select("SELECT * FROM `schedule` WHERE user_id = #{id} and date >= #{date}")
    List<ScheduleVO> selectVisitorSchedules(@Param("id")Long id,
                                           @Param("date")Date date);


    //初访师更新初访结论
    @Update("UPDATE visit_record\n" +
            "SET rank = #{rank},type = #{type },status = #{status},diag = #{diag},result = #{result}\n" +
            "WHERE id=#{id}")
    void update_visitRecord(@Param("rank")Integer rank,
                            @Param("type")String type,
                            @Param("status")Integer status,
                            @Param("diag")Integer diag,
                            @Param("result")String result,
                            @Param("id")Long id);

    List<Long> selectApplicationIdsByScheduleIds(@Param("schedules") List<Long> schedules);

    List<VisitRecordVO> selectByOrgId(Long org_id);
}

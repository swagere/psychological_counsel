package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.vo.VisitRecordScheduleVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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



    List<VisitRecordScheduleVO> selectByDateAndChecked(@Param("schedules") List<Long> schedules);

    //初访师查看自己的初访记录表（今天以及以后）



    //初访师查看自己的排班时间（今天以及以后）

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

}

package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.vo.ConsultRecordVO;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultRecordMapper extends BaseMapper<ConsultRecord> {


    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ConsultRecord consultRecord);

    @Update("UPDATE consult_record\n" +
            "SET evaluate = #{evaluate}\n" +
            "WHERE id = #{id} ")
    void update_evaluate(@Param("evaluate") String evaluate,
                         @Param("id")Long id);

    List<ConsultRecordVO> selectByConsultId(@Param("consult_id") Long consult_id);

    List<ConsultRecordVO> selectByOrgIdAndChecked(@Param("org_id") Long org_id);

    List<ConsultRecordVO> selectByDateAndChecked(@Param("schedules")List<Long> schedules1);

    boolean updateScheduleIdById(@Param("schedule_id") Long schedule_id, @Param("id") Long consultRecord_id);
}

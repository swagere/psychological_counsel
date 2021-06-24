package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.VisitRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.vo.VisitRecordVO;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRecordMapper extends BaseMapper<VisitRecord> {


    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(VisitRecord visitRecord);

    @Select("SELECT *\n" +
            "FROM visit_record\n" +
            "WHERE id = #{id}")
    List<VisitRecord> selectByID (@Param("id")Long id);



    List<VisitRecordVO> selectByDateAndChecked(@Param("schedules") List<Long> schedules);

    List<VisitRecordVO> selectByOrgId(@Param("org_id") Long org_id);
}

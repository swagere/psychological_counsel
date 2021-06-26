package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultRecordMapper extends BaseMapper<ConsultRecord> {


    //添加咨询记录表
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ConsultRecord consultRecord);

    //用户更新评论
    @Update("UPDATE consult_record\n" +
            "SET evaluate = #{evaluate}\n" +
            "WHERE id = #{id} ")
    void update_evaluate(@Param("evaluate") String evaluate,
                         @Param("id")Long id);

    //查看咨询记录表（根据consultId）
    @Select("SELECT *\n" +
            "FROM consult_record\n" +
            "WHERE consult_id = #{id}")
    List<ConsultRecord> selectConsultRecord(@Param("id")Long id);

    //查看咨询记录表的次数（根据Id）
    @Select("SELECT times\n" +
            "FROM consult_record\n" +
            "WHERE id = #{id}")
    Integer selectConsultRecordTimes(@Param("id")Long id);

    //查看咨询记录表的咨询表id（根据Id）
    @Select("SELECT consult_id\n" +
            "FROM consult_record\n" +
            "WHERE id = #{id}")
    Long selectConsultId(@Param("id")Long id);

    //修改咨询记录结论
    @Update("UPDATE consult_record\n" +
            "SET result = #{result}\n" +
            "WHERE id = #{id}")
    void update_consultRecordResult(@Param("result")String result,
                                    @Param("id")Long id);

    //添加咨询记录表状态
    @Update("UPDATE consult_record\n" +
            "SET `status` = #{status}\n" +
            "WHERE id = #{id}")
    void update_consultRecordStatus(@Param("status")Integer status,
                                    @Param("id")Long id);
}

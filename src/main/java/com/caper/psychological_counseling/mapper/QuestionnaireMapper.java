package com.caper.psychological_counseling.mapper;

import com.caper.psychological_counseling.model.domain.Questionnaire;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caper.psychological_counseling.model.vo.QuestionnaireVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionnaireMapper extends BaseMapper<Questionnaire> {

    //展示问卷
    @Select("SELECT * FROM questionnaire")
    List<QuestionnaireVO> show_questionnaire();



    //读取问题的分数
    @Select("SELECT grade\n" +
            "FROM questionnaire\n" +
            "WHERE id = #{id}")
    Integer get_grade(@Param("id")Long id);

    //读取问题的紧急情况
    @Select("SELECT is_urgent\n" +
            "FROM questionnaire\n" +
            "WHERE id = #{id}")
    Integer get_urgent(@Param("id")Long id);



}

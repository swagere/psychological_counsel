package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Questionnaire;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.QuestionnaireVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionnaireService extends IService<Questionnaire> {

    //展示问卷
    List<QuestionnaireVO> show_questionnaire();

    //读取问题的分数
    Integer get_grade(Long id);

    //读取问题的分紧急情况
    Integer get_urgent(Long id);

}

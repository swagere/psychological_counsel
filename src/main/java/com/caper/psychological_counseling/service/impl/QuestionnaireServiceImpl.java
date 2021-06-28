package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Questionnaire;
import com.caper.psychological_counseling.mapper.QuestionnaireMapper;
import com.caper.psychological_counseling.model.vo.QuestionnaireVO;
import com.caper.psychological_counseling.service.QuestionnaireService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {

    @Resource
    QuestionnaireMapper questionnaireMapper;

    @Override
    public List<QuestionnaireVO> show_questionnaire(){
        return questionnaireMapper.show_questionnaire();
    }

    @Override
    public Integer get_grade(Long id){
        return questionnaireMapper.get_grade(id);
    }

    @Override
    public Integer get_urgent(Long id){
        return questionnaireMapper.get_urgent(id);
    }

}

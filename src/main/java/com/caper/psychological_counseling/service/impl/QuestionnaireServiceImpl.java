package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Questionnaire;
import com.caper.psychological_counseling.mapper.QuestionnaireMapper;
import com.caper.psychological_counseling.service.QuestionnaireService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {

}

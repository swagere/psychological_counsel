package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.mapper.ConsultMapper;
import com.caper.psychological_counseling.service.ConsultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ConsultServiceImpl extends ServiceImpl<ConsultMapper, Consult> implements ConsultService {

}

package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Application;
import com.caper.psychological_counseling.mapper.ApplicationMapper;
import com.caper.psychological_counseling.service.ApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

}

package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.mapper.ConsultMapper;
import com.caper.psychological_counseling.model.vo.ScheduleVO;
import com.caper.psychological_counseling.service.ConsultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ConsultServiceImpl extends ServiceImpl<ConsultMapper, Consult> implements ConsultService {

    @Resource
    ConsultMapper consultMapper;

    @Override
    public List<ScheduleVO> find_consults(Long area_id, String type){
        Date date = new Date(new java.util.Date().getTime());

        return  consultMapper.find_consults(area_id, type, date);
    }
}

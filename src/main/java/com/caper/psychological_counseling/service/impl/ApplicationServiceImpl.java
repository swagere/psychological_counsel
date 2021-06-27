package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Application;
import com.caper.psychological_counseling.mapper.ApplicationMapper;
import com.caper.psychological_counseling.model.vo.ApplicationVO;
import com.caper.psychological_counseling.service.ApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: meidou
 */

@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {



    @Resource
    private ApplicationMapper applicationMapper;



    //建立初访记录表
    @Override
    public void buildApplication(Application application){

        applicationMapper.insert(application);

    }

    //查询自己的初访申请表
    @Override
    public List<ApplicationVO> get_application(Long user_id){

        return applicationMapper.get_application(user_id);

    }

    @Override
    public List<Application> getByIds(List<Long> application_ids) {
        return applicationMapper.selectBatchIds(application_ids);
    }

    //计算得分(id为初访申请表id)
    @Override
    public void update_grade(Integer grade, Long id){
        applicationMapper.update_grade(grade, id);
    }


    //评判危险等级(id为初访申请表id)
    @Override
    public void update_urgency(Integer urgency,Long id){
        applicationMapper.update_urgency(urgency, id);
    }

    //查询答案
    @Override
    public String select_answer(Long id){
        return applicationMapper.select_answer(id);
    }


    //导入答案
    @Override
    public void update_answer(Long id,String answer){
        applicationMapper.update_answer(id, answer);
    }
}

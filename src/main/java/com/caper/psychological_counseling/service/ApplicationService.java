package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Application;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.ApplicationVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * author: meidou
 */

public interface ApplicationService extends IService<Application> {

    //建立初访申请表
    void buildApplication(Application application);

    List<ApplicationVO> get_application(Long user_id);

    List<Application> getByIds(List<Long> application_ids);


    //计算得分(id为初访申请表id)
    void update_grade(Integer grade, Long id);


    //评判危险等级(id为初访申请表id)

    void update_urgency(Integer urgency,
                        Long id);

    //查询答案

    String select_answer(Long id);


    //导入答案

    void update_answer(Long id,String answer);
}

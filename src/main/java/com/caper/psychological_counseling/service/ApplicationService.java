package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Application;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.dto.ApplicationDTO;

/**
 * author: meidou
 */

public interface ApplicationService extends IService<Application> {

    //建立初访申请表
    void buildApplication(Application application);

    ApplicationDTO get_application(Long user_id);

}

package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrganizationService extends IService<Organization> {

    List<Long> getOrgIdsByLevel(Integer level);
}

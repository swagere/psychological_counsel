package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Organization;
import com.caper.psychological_counseling.mapper.OrganizationMapper;
import com.caper.psychological_counseling.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public List<Long> getOrgIdsByLevel(Integer level) {
        return organizationMapper.selectOrgIdsByLevel(level);
    }
}

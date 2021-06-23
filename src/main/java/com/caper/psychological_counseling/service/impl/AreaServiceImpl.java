package com.caper.psychological_counseling.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.model.domain.Area;
import com.caper.psychological_counseling.mapper.AreaMapper;
import com.caper.psychological_counseling.model.vo.AreaVO;
import com.caper.psychological_counseling.service.AreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<Long> getAreaIdsByOrgId(Long org_id) {
        return areaMapper.selectAreaIdsByOrgId(org_id);
    }

    @Override
    public List<AreaVO> getAreaVOByOrgId(Long org_id) {
        return areaMapper.selectAreaVOByOrgId(org_id);
    }
}

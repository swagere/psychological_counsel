package com.caper.psychological_counseling.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.model.domain.Area;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AreaService extends IService<Area> {

    List<Long> getAreaIdsByOrgId(Long org_id);
}

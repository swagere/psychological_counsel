package com.caper.psychological_counseling.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.model.domain.Area;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.AreaVO;

import java.util.List;

public interface AreaService extends IService<Area> {

    List<Long> getAreaIdsByOrgId(Long org_id);

    List<AreaVO> getAreaVOByOrgId(Long org_id);

    void saveArea(Area area);
}

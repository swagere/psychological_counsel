package com.caper.psychological_counseling.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.common.config.system.SysOrgNode;
import com.caper.psychological_counseling.common.config.system.utils.DataTreeUtil;
import com.caper.psychological_counseling.mapper.SystemMapper;
import com.caper.psychological_counseling.model.domain.Area;
import com.caper.psychological_counseling.mapper.AreaMapper;
import com.caper.psychological_counseling.model.domain.Organization;
import com.caper.psychological_counseling.model.vo.AreaVO;
import com.caper.psychological_counseling.service.AreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Resource
    private SystemMapper systemMapper;

    @Override
    public List<Long> getAreaIdsByOrgId(Long org_id) {
        return areaMapper.selectAreaIdsByOrgId(org_id);
    }

    @Override
    public List<AreaVO> getAreaVOByOrgId(Long org_id) {
        return areaMapper.selectAreaVOByOrgId(org_id);
    }

    @Override
    public void saveArea(Area area) {
        areaMapper.insert(area);
    }

    @Override
    public boolean updateArea(Area area) {
        return areaMapper.updateArea(area);
    }

    @Override
    public List<SysOrgNode> getAreaTreeById(Long rootOrgId, String orgNameLike, Boolean orgStatus) {
        if (rootOrgId != null) {
            List<Organization> sysOrgs
                    = systemMapper.selectAreaOrgTree(rootOrgId, orgNameLike, orgStatus);

            List<SysOrgNode> sysOrgNodes = sysOrgs.stream().map(item -> {
                SysOrgNode bean = new SysOrgNode();
                BeanUtils.copyProperties(item, bean);
                return bean;
            }).collect(Collectors.toList());

            if (StringUtils.isNotEmpty(orgNameLike)  || orgStatus != null) {
                return sysOrgNodes;//?????????????????????????????????????????????????????????????????????
            } else {//??????????????????????????????
                return DataTreeUtil.buildTree(sysOrgNodes, rootOrgId);
            }

        } else {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                    "???????????????????????????id????????????");
        }
    }

    @Override
    public List<SysOrgNode> getAreasByOrgId(Long org_id) {
        return systemMapper.selectAreasByOrgId(org_id);
    }

}

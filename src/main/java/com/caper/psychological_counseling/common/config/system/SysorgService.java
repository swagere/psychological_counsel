package com.caper.psychological_counseling.common.config.system;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.common.config.system.utils.DataTreeUtil;
import com.caper.psychological_counseling.mapper.SystemMapper;
import com.caper.psychological_counseling.model.domain.Organization;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysorgService {

  @Resource
  private SystemMapper systemMapper;

  public List<SysOrgNode> getOrgTreeById(Long rootOrgId,
                                         String orgNameLike,
                                         Boolean orgStatus) {
    if (rootOrgId != null) {
      List<Organization> sysOrgs
        = systemMapper.selectOrgTree(rootOrgId, orgNameLike, orgStatus);

      List<SysOrgNode> sysOrgNodes = sysOrgs.stream().map(item -> {
        SysOrgNode bean = new SysOrgNode();
        BeanUtils.copyProperties(item, bean);
        return bean;
      }).collect(Collectors.toList());

      if (StringUtils.isNotEmpty(orgNameLike)  || orgStatus != null) {
        return sysOrgNodes;//根据条件查询，会破坏树形结构，所以返回平面列表
      } else {//否则返回树型结构列表
        return DataTreeUtil.buildTree(sysOrgNodes, rootOrgId);
      }

    } else {
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "查询参数用户名组织id不能为空");
    }
  }
}
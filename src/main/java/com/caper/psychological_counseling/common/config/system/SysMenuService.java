package com.caper.psychological_counseling.common.config.system;

import com.caper.psychological_counseling.common.config.system.utils.DataTreeUtil;
import com.caper.psychological_counseling.mapper.SystemMapper;
import com.caper.psychological_counseling.model.domain.SysMenu;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuService {
    @Resource
    private SystemMapper systemMapper;

    public List<SysMenuNode> getMenuTreeByUsername(String username) {
        List<SysMenu> sysMenus = systemMapper.selectMenuByUsername(username);

        if (sysMenus.size() > 0) {
            Long rootMenuId = sysMenus.get(0).getId();

            List<SysMenuNode> sysOrgNodes =
                    sysMenus.stream().map(item -> {
                        SysMenuNode bean = new SysMenuNode();
                        BeanUtils.copyProperties(item, bean);
                        return bean;
                    }).collect(Collectors.toList());
            return DataTreeUtil.buildTreeWithoutRoot(sysOrgNodes, rootMenuId);
        }
        return new ArrayList<>();
    }
}

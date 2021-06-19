package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.common.config.utils.DataTreeUtil;
import com.caper.psychological_counseling.mapper.SysMenuMapper;
import com.caper.psychological_counseling.mapper.SystemMapper;
import com.caper.psychological_counseling.model.domain.SysMenu;
import com.caper.psychological_counseling.model.domain.SysMenuNode;
import com.caper.psychological_counseling.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SystemMapper systemMapper;

    public List<SysMenuNode> getMenuTreeByUsername(String username) {
        //根据用户名查询该用户可以访问的菜单
        List<SysMenu> sysMenus = systemMapper.selectMenuByUsername(username);

        if (sysMenus.size() > 0) {
            Long rootMenuId = sysMenus.get(0).getId(); //第一条记录为根节点
            //将List<SysMenu>转成List<SysMenuNode>
            List<SysMenuNode> sysMenuNodes =
                    sysMenus.stream().map(item -> {
                        SysMenuNode bean = new SysMenuNode();
                        BeanUtils.copyProperties(item, bean);
                        return bean;
                    }).collect(Collectors.toList());
            //构建树形结构节点列表
            return DataTreeUtil.buildTreeWithoutRoot(sysMenuNodes, rootMenuId);
        }
        return new ArrayList<>();
    }
}

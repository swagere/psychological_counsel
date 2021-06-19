package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.domain.SysMenuNode;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {
    public List<SysMenuNode> getMenuTreeByUsername(String username);

}

package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.model.domain.SysMenuNode;
import com.caper.psychological_counseling.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author:chou
 *
 * 中心管理员
 * 系统管理
 */

@Slf4j
@RestController("/system")
public class SystemSystemController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/tree/user", method = RequestMethod.GET)
    public List<SysMenuNode> userTree() {
        return sysMenuService.getMenuTreeByUsername("201801");
    }
}

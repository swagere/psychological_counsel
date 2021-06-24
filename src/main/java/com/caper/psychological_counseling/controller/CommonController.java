package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.system.SysMenuNode;
import com.caper.psychological_counseling.common.config.system.SysMenuService;
import com.caper.psychological_counseling.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author:chou
 *
 * 通用类
 */

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/tree/user/{id}", method = RequestMethod.GET)
    public List<SysMenuNode> userTree(@PathVariable("id") Long id) {
        String username = sysUserService.getUserNameByUserId(id);
        return sysMenuService.getMenuTreeByUsername(username);
    }
}

package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.system.SysMenuNode;
import com.caper.psychological_counseling.common.config.system.SysMenuService;
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
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/tree/user/{username}", method = RequestMethod.GET)
    public List<SysMenuNode> userTree(@PathVariable("username") String username) {
        return sysMenuService.getMenuTreeByUsername(username);
    }
}

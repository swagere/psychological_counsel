package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.system.SysMenuNode;
import com.caper.psychological_counseling.common.config.system.SysMenuService;
import com.caper.psychological_counseling.common.config.system.SysOrgNode;
import com.caper.psychological_counseling.common.config.system.SysorgService;
import com.caper.psychological_counseling.service.OrganizationService;
import com.caper.psychological_counseling.service.SysUserService;
import com.caper.psychological_counseling.model.dto.UserDTO;
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
    @Autowired
    private SysorgService sysorgService;
    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/tree/user/{id}", method = RequestMethod.GET)
    public List<SysMenuNode> getUserTree(@PathVariable("id") Long id) {
        String username = sysUserService.getUserNameByUserId(id);
        return sysMenuService.getMenuTreeByUsername(username);
    }

    //获取用户信息，根据当前ID获取

    @RequestMapping(value = "/user/getUser/{id}", method =  RequestMethod.GET)
    public AjaxResponse getUser(@PathVariable("id")Long id){
        //System.out.println(id);
        UserDTO userDTO = sysUserService.getSysUser(id);
        System.out.println(userDTO);

        return AjaxResponse.success(userDTO);

    }


    //修改用户信息
    @PutMapping("/user/update")
    public AjaxResponse updateUser(@RequestParam("id") Long id,
                                   @RequestParam("telephone")Long telephone,
                                   @RequestParam("email")String email,
                                   @RequestParam("gender")Integer gender,
                                   @RequestParam("description")String description){

        if(id == null){

            //异常
        }

        System.out.println(id);
        sysUserService.updateSysUser(id,telephone,email,gender,description);

        return AjaxResponse.success();
    }


    //注销账号（删除用户信息）

    @DeleteMapping("/user/{id}")
    public AjaxResponse deleteUser(@PathVariable("id")Long id){

        sysUserService.deleteSysUser(id);
        return AjaxResponse.success();
    }

    /**
     * 获得组织
     * 树信息
     */
    @RequestMapping(value = "/tree/org/{org_id}", method = RequestMethod.GET)
    public List<SysOrgNode> getOrgTree(@PathVariable("org_id") Long org_id) {
        //获取根节点id
        String pids = organizationService.getById(org_id).getOrgIds();
        try {
            Long root_id = Long.valueOf(pids.split(",")[1].replace("[", "").replace("]", ""));
            return sysorgService.getOrgTreeById(root_id, null, null);
        }catch (Exception e) {
            Long root_id = org_id;
            return sysorgService.getOrgTreeById(root_id, null, null);
        }
    }

}

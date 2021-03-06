package com.caper.psychological_counseling.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.common.config.system.SysMenuNode;
import com.caper.psychological_counseling.common.config.system.SysMenuService;
import com.caper.psychological_counseling.common.config.system.SysOrgNode;
import com.caper.psychological_counseling.common.config.system.SysorgService;
import com.caper.psychological_counseling.model.domain.Area;
import com.caper.psychological_counseling.model.domain.SysUser;
import com.caper.psychological_counseling.service.AreaService;
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
    @Autowired
    private AreaService areaService;

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
//            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"豆豆傻逼"));
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"此处只能添加不能修改");
        }

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
        Long root_id = org_id;
        try {
            root_id = Long.valueOf(pids.split(",")[1].replace("[", "").replace("]", ""));

        }catch (Exception e) {}
        return sysorgService.getOrgTreeById(root_id, null, null);
    }

    /**
     * 获得校区
     * 树信息
     */
    @RequestMapping(value = "/tree/area/{org_id}", method = RequestMethod.GET)
    public List<SysOrgNode> getAreaTree(@PathVariable("org_id") Long org_id) {
        //获取根节点id
        String pids = organizationService.getById(org_id).getOrgIds();
        Long root_id = org_id;
        try {
            root_id = Long.valueOf(pids.split(",")[1].replace("[", "").replace("]", ""));

        }catch (Exception e) {}

        List<SysOrgNode> orgs =  areaService.getAreaTreeById(root_id, null, null);
        for (SysOrgNode org : orgs) {
            List<SysOrgNode> children = org.getChildren();

            for (SysOrgNode child : children) {
                //查出org_id下的所有教室
                //将教室转化为SysOrgNode

                System.out.println(child.getId());
                try {
                    List<SysOrgNode> areas = areaService.getAreasByOrgId(child.getId());

                    //赋值给children
                    child.setChildren(areas);
                    child.setLeaf(0);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

        }

        return orgs;
    }

    /**
     * 中心管理员与咨询助理
     * 排班
     * 输入 校区和角色
     * 获取用户及id
     */
    @RequestMapping(value = "/users/org/{org_id}/role/{role_id}", method = RequestMethod.GET)
    public AjaxResponse getUsersByOrgIdAndRoleId(@PathVariable Long org_id, @PathVariable Long role_id) {
        return AjaxResponse.success(sysUserService.getByOrgIdAndRoleId(org_id, role_id));
    }


}

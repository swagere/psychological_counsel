package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.common.config.system.SysMenuNode;
import com.caper.psychological_counseling.common.config.system.SysMenuService;
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

    @RequestMapping(value = "/tree/user/{id}", method = RequestMethod.GET)
    public List<SysMenuNode> userTree(@PathVariable("id") Long id) {
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
}

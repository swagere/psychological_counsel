package com.caper.psychological_counseling.common.config.auth.jwt;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.service.SysUserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@RestController
public class JwtAuthController {

    @Resource
    JwtAuthService jwtAuthService;

    @Resource
    SysUserService sysUserService;

    /**
     * 登陆认证：学号登陆
     * 获得jwt令牌
     * @param map
     * @return
     */
    @RequestMapping(value = "/authentication")
    public AjaxResponse login(@RequestBody Map<String,String> map, HttpServletResponse response){
        String username = map.get("username");
        String password = map.get("password");

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return AjaxResponse.error(
                    new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                    "用户名或者密码不能为空"));
        }
        try {
            HashMap res = new HashMap();
            res.put("token", jwtAuthService.login(username, password));
            res.put("id", sysUserService.getUserIdByUserName(username));
            return AjaxResponse.success(res);
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }

    /**
     * 更新令牌
     * 用旧令牌换新令牌
     * @param token
     * @return
     */
    @RequestMapping(value = "/refreshToken")
    public  AjaxResponse refresh(@RequestHeader("${jwt.header}") String token){
            String newToken = jwtAuthService.refreshToken(token);
            if (newToken == null) {
                return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                        "旧令牌认证失败"));
            }
            return AjaxResponse.success(newToken);
    }

}

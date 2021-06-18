package com.caper.psychological_counseling.common.config.auth.jwt;

import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JwtAuthService {

    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    UserDetailsService userDetailsService;

    @Resource
    JwtTokenUtil jwtTokenUtil;

    // 登录认证获取JWT令牌（令牌为string类型）
    public String login(String username,String password) throws CustomException {
        try {
            UsernamePasswordAuthenticationToken upToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (AuthenticationException e){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR
                            ,"学号/工号或者密码不正确");
        }

        //登陆认证已成功
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);  //生成jwt令牌
    }

    // 更新令牌
    public String refreshToken(String oldToken){
        if(!jwtTokenUtil.isTokenExpired(oldToken)){
            //如果token没有过期，则刷新token
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }



}

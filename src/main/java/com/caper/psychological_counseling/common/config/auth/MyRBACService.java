package com.caper.psychological_counseling.common.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Component("rabcService")
public class MyRBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 判断某用户是否具有该request资源的访问权限
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;

            String currentURI = request.getRequestURI().replace("/api","");


            //本次需要访问的资源
            //从内存中获取权限，提升效率
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(currentURI);
            return userDetails.getAuthorities().contains(simpleGrantedAuthority);

        }

        return false;
    }
}

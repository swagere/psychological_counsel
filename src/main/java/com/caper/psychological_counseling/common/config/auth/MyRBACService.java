package com.caper.psychological_counseling.common.config.auth;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Component("rabcService")
public class MyRBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 判断某用户是否具有该request资源的访问权限（页面）
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;

            String currentURI = request.getRequestURI().replace("/api","");


            //本次需要访问的资源
            //从内存中获取权限，提升效率
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(currentURI);

            return userDetails.getAuthorities().contains(authorityList.get(0));

        }

        return false;
    }
}

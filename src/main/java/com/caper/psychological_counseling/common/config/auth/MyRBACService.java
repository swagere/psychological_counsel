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
import java.util.Collection;
import java.util.List;
import java.util.regex.*;


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
//            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(currentURI);

            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            boolean flag = false;
            for (GrantedAuthority authority : authorities) {
                String pattern = authority.getAuthority();
                if (Pattern.matches(pattern, currentURI)) {
                    flag = true;
                    break;
                }
            }
            System.out.println(flag);
            return flag;

        }

        return false;
    }
}

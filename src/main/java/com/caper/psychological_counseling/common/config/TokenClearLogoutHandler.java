package com.caper.psychological_counseling.common.config;

import com.caper.psychological_counseling.common.config.auth.MyUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenClearLogoutHandler implements LogoutHandler {
    
    private MyUserDetailsService myUserDetailsService;
    
    public TokenClearLogoutHandler(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        clearToken(authentication);
    }
    
    protected void clearToken(Authentication authentication) {
        if(authentication == null)
            return;
        UserDetails user = (UserDetails)authentication.getPrincipal();
//        if(user!=null && user.getUsername()!=null)
//            myUserDetailsService.deleteUserLoginInfo(user.getUsername());
    }

}
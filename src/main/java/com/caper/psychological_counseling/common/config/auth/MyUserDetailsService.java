package com.caper.psychological_counseling.common.config.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //用户基础数据加载
        MyUserDetails userDetails = myUserDetailsServiceMapper.findByUsername(s);

        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名/密码不存在");
        }

        //用户的角色列表
        List<String> roleCodes = myUserDetailsServiceMapper.findRoleByUsername(s);

        //根据角色加载权限
        List<String> authorities = myUserDetailsServiceMapper.findAuthorityByRoleCodes(roleCodes);

        roleCodes.stream()
                .map(rc->"ROLE_" + rc)
                .collect(Collectors.toList());
        authorities.addAll(roleCodes);
        userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(
                String.join(",",authorities)
        ));

        return userDetails;
    }
}

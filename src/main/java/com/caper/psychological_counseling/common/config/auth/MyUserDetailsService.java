package com.caper.psychological_counseling.common.config.auth;

import com.caper.psychological_counseling.mapper.MyUserDetailsServiceMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private MyUserDetailsServiceMapper myUserDetailsServiceMapper;

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
        List<String> authorities = new ArrayList<>();
        for(String roleCode : roleCodes){
            //通过用户角色列表加载用户的资源权限列表
            authorities.addAll(myUserDetailsServiceMapper.findApiByRoleCode(roleCode));
        }

        //角色是一个特殊的权限，ROLE_前缀
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

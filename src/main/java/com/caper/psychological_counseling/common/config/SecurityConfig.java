package com.caper.psychological_counseling.common.config;

import com.caper.psychological_counseling.common.config.auth.MyAccessDeniedHandler;
import com.caper.psychological_counseling.common.config.auth.MyAuthenticationEntryPoint;
import com.caper.psychological_counseling.common.config.auth.MyLogoutSuccessHandler;
import com.caper.psychological_counseling.common.config.auth.MyUserDetailsService;
import com.caper.psychological_counseling.common.config.auth.jwt.JwtAuthenticationTokenFilter;
import com.caper.psychological_counseling.common.config.auth.jwt.TokenClearLogoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    MyUserDetailsService myUserDetailsService;

    @Resource
    private DataSource datasource;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Resource
    private TokenClearLogoutHandler tokenClearLogoutHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
                .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/logout")
//                .addLogoutHandler(tokenClearLogoutHandler)
                .logoutSuccessHandler(myLogoutSuccessHandler)
//             .and().rememberMe()
//                .rememberMeParameter("remember-me-new")
//                .rememberMeCookieName("remember-me-cookie")
//                .tokenValiditySeconds(2 * 24 * 60 * 60)
//                .tokenRepository(persistentTokenRepository())
             .and().csrf().disable()  //????????????????????????
             .authorizeRequests()
                .antMatchers("/authentication","/refreshToken").permitAll()
                .antMatchers().authenticated()
                .anyRequest().access("@rabcService.hasPermission(request,authentication)")
             .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //?????????session
             .and().exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler)
                .authenticationEntryPoint(myAuthenticationEntryPoint);
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService) //???????????????????????????????????????
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(datasource);

        return tokenRepository;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

package com.idlelong.loginsecurity.config;

import com.idlelong.loginsecurity.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * 登录安全配置
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@Configuration
public class LoginSecurityConfig {

    @Resource
    private UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> userService.loadUserByUsername(username);
    }
}

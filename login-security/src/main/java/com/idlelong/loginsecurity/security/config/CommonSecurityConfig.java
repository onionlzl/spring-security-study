package com.idlelong.loginsecurity.security.config;

import com.idlelong.loginsecurity.security.compoent.MyDecisionManager;
import com.idlelong.loginsecurity.security.compoent.MyDeniedHandler;
import com.idlelong.loginsecurity.security.compoent.MyEntryPoint;
import com.idlelong.loginsecurity.security.compoent.MySecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurity通用配置
 * 包括通用Bean、Security通用Bean及动态权限通用Bean
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@Configuration
public class CommonSecurityConfig{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyEntryPoint myEntryPoint() {
        return new MyEntryPoint();
    }

    @Bean
    public MyDeniedHandler myDeniedHandler() {
        return new MyDeniedHandler();
    }

    @Bean
    public MySecurityMetadataSource mySecurityMetadataSource(){
        return new MySecurityMetadataSource();
    }

    @Bean
    public MyDecisionManager myDecisionManager(){
        return new MyDecisionManager();
    }

}
package com.idlelong.loginsecurity;

import com.idlelong.security.common.config.ExceptionControllerAdvice;
import com.idlelong.security.common.config.JavaTimeConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 登录安全应用程序
 *
 * @author lizhenlong
 * @date 2023/06/08
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.idlelong.loginsecurity.mapper"})
@Import({ExceptionControllerAdvice.class, JavaTimeConfig.class})
public class LoginSecurityApplication{
    public static void main(String[] args) {
        SpringApplication.run(LoginSecurityApplication.class,args);
    }

}

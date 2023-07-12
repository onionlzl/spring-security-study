package com.idlelong.auth;

import com.idlelong.security.common.config.ExceptionControllerAdvice;
import com.idlelong.security.common.config.JavaTimeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * oauth2身份验证应用程序
 *
 * @author lizhenlong
 * @date 2023/07/02
 */
@SpringBootApplication
@Import({ExceptionControllerAdvice.class, JavaTimeConfig.class})
public class Oauth2AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthApplication.class,args);
    }
}

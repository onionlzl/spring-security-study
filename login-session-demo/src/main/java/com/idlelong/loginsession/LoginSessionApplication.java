package com.idlelong.loginsession;

import com.idlelong.loginsession.config.ResponseControllerAdvice;
import com.idlelong.security.common.config.ExceptionControllerAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 登录Session应用程序
 *
 * @author lizhenlong
 * @date 2023/06/01
 */
@SpringBootApplication
@Import({ExceptionControllerAdvice.class, ResponseControllerAdvice.class})
public class LoginSessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginSessionApplication.class,args);
    }
}

package com.idlelong.rbac;

import com.idlelong.security.common.config.ExceptionControllerAdvice;
import com.idlelong.security.common.config.JavaTimeConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * rbac应用程序
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.idlelong.rbac.mapper"})
@Import({ExceptionControllerAdvice.class, JavaTimeConfig.class})
public class RbacApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbacApplication.class,args);
    }
}

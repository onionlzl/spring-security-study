package com.idlelong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 客户端证书应用程序
 *
 * @author lizhenlong
 * @date 2023/06/14
 */
@SpringBootApplication
public class Oauth2JwtServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2JwtServerApplication.class,args);
    }
}

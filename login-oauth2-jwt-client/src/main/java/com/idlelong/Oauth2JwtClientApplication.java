package com.idlelong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * 客户端证书应用程序
 *
 * @author lizhenlong
 * @date 2023/06/14
 */
@SpringBootApplication
@EnableOAuth2Sso
public class Oauth2JwtClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2JwtClientApplication.class,args);
    }
}

package com.idlelong.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * oauth2 api应用程序
 *
 * @author lizhenlong
 * @date 2023/07/13
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ApiApplication.class);
    }
}

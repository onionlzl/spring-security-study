package com.idlelong.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * oauth2网关应用程序
 *
 * @author lizhenlong
 * @date 2023/07/03
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2GatewayApplication.class,args);
    }
}

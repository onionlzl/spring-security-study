package com.idlelong.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author lizhenlong
 * @date 2023/07/13
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World.";
    }
}

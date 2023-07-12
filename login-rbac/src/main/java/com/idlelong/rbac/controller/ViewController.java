package com.idlelong.rbac.controller;

import com.idlelong.security.common.annotation.NotResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 视图控制器
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
@RestController
public class ViewController {

    @GetMapping("/")
    @NotResponseBody
    public String index(HttpServletRequest request) {
        return "index";
    }
}

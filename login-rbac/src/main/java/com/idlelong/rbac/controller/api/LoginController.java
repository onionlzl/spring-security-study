package com.idlelong.rbac.controller.api;

import com.idlelong.rbac.req.LoginReq;
import com.idlelong.rbac.service.UserService;
import com.idlelong.rbac.vo.UserVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录控制器
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
@RestController
@RequestMapping("/API")
public class LoginController {

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @PostMapping("/login")
    public UserVO login(@Valid @RequestBody LoginReq loginReq) {
        // 这里简单点就只返回一个权限路径集合
        return userService.login(loginReq);
    }
}
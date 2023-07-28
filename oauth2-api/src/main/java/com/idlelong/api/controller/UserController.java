package com.idlelong.api.controller;

import com.idlelong.api.domain.UserDTO;
import com.idlelong.api.holder.LoginUserHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author lizhenlong
 * @date 2023/07/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }
}

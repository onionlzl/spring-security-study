package com.idlelong.loginjwt.controller;

import com.idlelong.loginjwt.context.UserContext;
import com.idlelong.loginjwt.entity.User;
import com.idlelong.loginjwt.service.UserService;
import com.idlelong.loginjwt.util.JwtUtil;
import com.idlelong.security.common.api.CommonResult;
import com.idlelong.security.common.api.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * jwt控制器
 *
 * @author lizhenlong
 * @date 2023/06/02
 */
@RestController
@RequestMapping("/jwt")
public class JwtController {

    private final UserService userService;

    public JwtController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody User user) {
        // 判断账号密码是否正确，这一步肯定是要读取数据库中的数据来进行校验的，这里为了模拟就省去了
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            // 如果正确的话就返回生成的token（注意哦，这里服务端是没有存储任何东西的）
            return CommonResult.success(JwtUtil.generateJwt(user.getUsername()));
        }
        return CommonResult.failed(ResultCode.UNAUTHORIZED, "账号密码错误");
    }

    @GetMapping("/api")
    public CommonResult<String> api() {
        userService.doSomething();
        return CommonResult.success("api->当前登录用户: " + UserContext.getCurrentUserName());
    }

    @GetMapping("/api2")
    public CommonResult<String> api2() {
        return CommonResult.success("ap2->当前登录用户: " + UserContext.getCurrentUserName());
    }
}
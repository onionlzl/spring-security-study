package com.idlelong.loginsession.controller;

import com.idlelong.loginsession.context.RequestContext;
import com.idlelong.loginsession.entity.User;
import com.idlelong.loginsession.service.UserService;
import com.idlelong.security.common.api.CommonResult;
import com.idlelong.security.common.api.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 会话控制器
 *
 * @author lizhenlong
 * @date 2023/06/01
 */
@RestController
public class SessionController {

    private final UserService userService;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public CommonResult<String> login(@Valid  @RequestBody User user, HttpSession session){
        // 判断账号密码是否正确，模拟比对
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            session.setAttribute("user",user);
            return CommonResult.success("登录成功");
        }
        return CommonResult.failed(ResultCode.UNAUTHORIZED,"账号或密码错误");
    }

    @GetMapping("/logout")
    public CommonResult<String> logout(HttpSession session){
        // 退出登录就是将用户信息删除
        session.removeAttribute("user");
        return CommonResult.success("退出成功");
    }

    @GetMapping("/api")
    public CommonResult<String> api() {
        userService.doSomething();
        return CommonResult.success("成功返回数据api");
    }

    @GetMapping("/api2")
    public CommonResult<User> api2() {
        User currentUser = RequestContext.getCurrentUser();
        return CommonResult.success(currentUser);
    }

}

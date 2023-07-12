package com.idlelong.loginsecurity.controller.api;

import com.idlelong.loginsecurity.param.LoginParam;
import com.idlelong.loginsecurity.service.UserService;
import com.idlelong.loginsecurity.vo.UserVO;
import com.idlelong.security.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 登录控制器
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@RestController
@RequestMapping("/API")
@Slf4j
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public CommonResult<UserVO> login(@Valid @RequestBody LoginParam param) {
        UserVO userVO =  userService.login(param);
        return CommonResult.success(userVO);
    }

    @GetMapping("/test")
    public CommonResult<String> test() {
        log.info("---test---");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.toString());
        return CommonResult.success("认证通过");
    }

}
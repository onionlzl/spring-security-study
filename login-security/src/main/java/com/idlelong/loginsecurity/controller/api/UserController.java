package com.idlelong.loginsecurity.controller.api;

import com.idlelong.loginsecurity.annotation.Auth;
import com.idlelong.loginsecurity.service.ResourceService;
import com.idlelong.loginsecurity.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 用户控制器
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
@RestController
@RequestMapping("/API/user")
@Auth(id = 1000, name = "用户管理")
@Validated
public class UserController {
    private final UserService userService;
    private final ResourceService resourceService;

    public UserController(UserService userService, ResourceService resourceService) {
        this.userService = userService;
        this.resourceService = resourceService;
    }
    @Auth(id = 3, name = "根据用户id获取资源ids")
    @GetMapping("/resources/{userId}")
    public Set<Long> getResourcesByUserId(@NotNull(message = "用户ID不能为空") @PathVariable("userId") Long userId) {
        return resourceService.getIdsByUserId(userId);
    }
    @Auth(id = 4, name = "用于演示路径参数")
    @GetMapping("/test/{id}")
    public String testInterface(@PathVariable("id") String id) {
        return "测试";
    }
}

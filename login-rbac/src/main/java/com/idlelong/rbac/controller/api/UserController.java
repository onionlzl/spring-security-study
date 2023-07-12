package com.idlelong.rbac.controller.api;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.idlelong.rbac.annotation.Auth;
import com.idlelong.rbac.req.UserPageReq;
import com.idlelong.rbac.req.UserReq;
import com.idlelong.rbac.service.ResourceService;
import com.idlelong.rbac.service.UserService;
import com.idlelong.rbac.vo.UserPageVO;
import com.idlelong.security.common.api.CommonPage;
import com.idlelong.security.common.api.ResultCode;
import com.idlelong.security.common.exception.BizException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
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

    @PostMapping
    @Auth(id = 1, name = "新增用户")
    public String createUser(@RequestBody @Validated(UserReq.CreateUser.class) UserReq param) {
        userService.createUser(param);
        return "操作成功";
    }

    @DeleteMapping
    @Auth(id = 2, name = "删除用户")
    public String deleteUser(@Valid @NotNull @Size(min = 1) Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            throw new BizException(ResultCode.BIZ_VALIDATE_FAILED);
        }
        userService.removeByUserIds(Arrays.asList(ids));
        return "操作成功";
    }

    @PutMapping
    @Auth(id = 3, name = "编辑用户")
    public String updateRoles(@RequestBody @Validated(UserReq.Update.class) UserReq param) {
        userService.update(param);
        return "操作成功";
    }

    @PostMapping("/page")
    public CommonPage<UserPageVO> getPage(@Valid @RequestBody UserPageReq userPageReq) {
        return userService.selectPage(userPageReq);
    }



    @GetMapping("/resources/{userId}")
    public Set<Long> getResourcesByUserId(@PathVariable("userId") Long userId) {
        return resourceService.getIdsByUserId(userId);
    }

    @Auth(id = 4, name = "用于演示路径参数")
    @GetMapping("/test/{id}")
    public String testInterface(@PathVariable("id") String id) {
        return "测试";
    }
}


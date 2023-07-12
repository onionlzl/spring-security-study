package com.idlelong.loginsession.service.impl;

import cn.hutool.json.JSONUtil;
import com.idlelong.loginsession.context.RequestContext;
import com.idlelong.loginsession.entity.User;
import com.idlelong.loginsession.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户服务impl
 *
 * @author lizhenlong
 * @date 2023/06/02
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public void doSomething() {
        User currentUser = RequestContext.getCurrentUser();
        log.info("service层--->当前登录用户对象: {}", JSONUtil.toJsonStr(currentUser));
    }
}

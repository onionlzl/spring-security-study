package com.idlelong.loginjwt.service.impl;

import com.idlelong.loginjwt.context.UserContext;
import com.idlelong.loginjwt.service.UserService;
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
        String currentUserName = UserContext.getCurrentUserName();
        log.info("service层--->当前登录用户对象: {}", currentUserName);
    }
}

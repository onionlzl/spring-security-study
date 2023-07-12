package com.idlelong.loginsecurity.service;

import com.idlelong.loginsecurity.param.LoginParam;
import com.idlelong.loginsecurity.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户服务
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return {@link UserDetails}
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 登录
     *
     * @param param 参数
     * @return {@link UserVO}
     */
    UserVO login(LoginParam param);
}

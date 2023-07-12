package com.idlelong.rbac.service;

import com.idlelong.rbac.req.LoginReq;
import com.idlelong.rbac.req.UserPageReq;
import com.idlelong.rbac.req.UserReq;
import com.idlelong.rbac.vo.UserPageVO;
import com.idlelong.rbac.vo.UserVO;
import com.idlelong.security.common.api.CommonPage;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户服务
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
public interface UserService {
    /**
     * 登录
     *
     * @param loginReq 登录请求
     * @return {@link UserVO}
     */
    UserVO login(LoginReq loginReq);

    /**
     * 创建用户
     *
     * @param param 参数
     */
    void createUser(UserReq param);

    /**
     * 删除用户id
     *
     * @param userIds 用户id
     */
    void removeByUserIds(List<Long> userIds);

    /**
     * 更新
     *
     * @param param 参数
     */
    void update(UserReq param);

    /**
     * 获取分页信息
     *
     * @param userPageReq
     * @return {@link CommonPage}<{@link UserPageVO}>
     */
    CommonPage<UserPageVO> selectPage(@Valid UserPageReq userPageReq);
}

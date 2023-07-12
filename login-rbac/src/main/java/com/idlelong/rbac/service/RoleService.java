package com.idlelong.rbac.service;

import java.util.List;

/**
 * 角色服务
 *
 * @author lizhenlong
 * @date 2023/06/05
 */
public interface RoleService {
    /**
     * 插入角色用户id
     *
     * @param roleIds 角色id
     * @param userId  用户id
     */
    void insertRolesByUserId(Long userId, List<Long> roleIds);

    /**
     * 删除用户id
     *
     * @param userIds 用户id
     */
    void removeByUserIds(List<Long> userIds);
}

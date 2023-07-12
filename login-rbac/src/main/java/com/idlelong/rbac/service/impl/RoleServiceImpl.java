package com.idlelong.rbac.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.idlelong.rbac.mapper.RoleMapper;
import com.idlelong.rbac.mapper.UserRoleMapper;
import com.idlelong.rbac.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色服务impl
 *
 * @author lizhenlong
 * @date 2023/06/05
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void insertRolesByUserId(Long userId, List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)){
            return;
        }
        roleMapper.insertRolesByUserId(userId, roleIds);
    }

    @Override
    public void removeByUserIds(List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)){
            return;
        }
        userRoleMapper.removeByUserIds(userIds);
    }
}

package com.idlelong.rbac.service;

import com.idlelong.rbac.constant.BizConstant;
import com.idlelong.rbac.entity.ResourceEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 资源服务
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
public interface ResourceService {
    /**
     * 删除资源类型
     *
     * @param resouceType 资源类型
     */
    void deleteResourceByType(BizConstant.ResouceType resouceType);

    /**
     * 批量插入
     *
     * @param resourceEntityList 资源列表
     */
    void batchInsert(List<ResourceEntity> resourceEntityList);

    /**
     * 查询所有
     *
     * @return {@link List}<{@link ResourceEntity}>
     */
    List<ResourceEntity> queryAll();

    /**
     * 通过用户id获取资源
     *
     * @param currentUserId 当前用户id
     * @return {@link List}<{@link ResourceEntity}>
     */
    List<ResourceEntity> getResourcesByUserId(Long currentUserId);

    /**
     * 根据用户id获取该用户的所有权限id
     *
     * @param userId 用户id
     * @return {@link Set}<{@link Long}>
     */
    Set<Long> getIdsByUserId(Long userId);
}

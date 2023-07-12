package com.idlelong.loginsecurity.service;

import com.idlelong.loginsecurity.constant.BizConstant;
import com.idlelong.loginsecurity.entity.ResourceEntity;

import java.util.List;
import java.util.Set;

/**
 * 资源服务
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
public interface ResourceService {
    /**
     * 被用户id id
     *
     * @param id id
     * @return {@link Set}<{@link Long}>
     */
    Set<Long> getIdsByUserId(Long id);

    /**
     * 删除资源类型
     *
     * @param interFaceType 国际米兰面临类型
     */
    void deleteResourceByType(BizConstant.ResouceType interFaceType);

    /**
     * 批量插入
     *
     * @param resourceEntityList 资源实体列表
     */
    void batchInsert(List<ResourceEntity> resourceEntityList);
}

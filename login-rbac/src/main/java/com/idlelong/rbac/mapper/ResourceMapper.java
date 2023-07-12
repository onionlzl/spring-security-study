package com.idlelong.rbac.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idlelong.rbac.entity.ResourceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 资源(ResourceEntity)表数据库访问层
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
public interface ResourceMapper extends BaseMapper<ResourceEntity> {

    /**
     * 按类型删除
     *
     * @param type 类型
     * @return int
     */
    int deleteByType(@Param("type") Integer type);

    /**
     * 插入批
     *
     * @param resourceEntityCollection 资源集合
     * @return int
     */
    int insertBatch(@Param("resourceEntityCollection") Collection<ResourceEntity> resourceEntityCollection);

    /**
     * 根据用户id获取该用户的所有权限资源对象
     *
     * @param userId 用户id
     * @return {@link List}<{@link ResourceEntity}>
     */
    List<ResourceEntity> selectListByUserId(Long userId);

    /**
     * 根据用户id获取权限ids
     *
     * @param userId 用户id
     * @return {@link Set}<{@link Long}>
     */
    Set<Long> selectIdsByUserId(Long userId);
}


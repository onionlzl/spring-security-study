package com.idlelong.loginsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idlelong.loginsecurity.entity.ResourceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 资源(Resource)表数据库访问层
 *
 * @author idlelong
 * @since 2023-06-12 16:42:01
 */
public interface ResourceMapper extends BaseMapper<ResourceEntity> {

    /**
     * 查询资源ids根据用户ID
     *
     * @param userId 用户id
     * @return {@link Set}<{@link Long}>
     */
    Set<Long> selectIdsByUserId(Long userId);

    /**
     * 批量插入资源
     *
     * @param resourceEntityList 资源实体列表
     */
    void insertBatch(@Param("resourceEntityList") List<ResourceEntity> resourceEntityList);

    /**
     * 根据资源类型删除资源
     *
     * @param resouceType 资源类型
     */
    void deleteByType(@Param("resouceType") Integer resouceType);
}


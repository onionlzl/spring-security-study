package com.idlelong.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idlelong.rbac.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户-角色关系表(UserRoleEntity)表数据库访问层
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    /**
     * 删除用户id
     *
     * @param userIds 用户id
     */
    void removeByUserIds(@Param("userIds") List<Long> userIds);
}


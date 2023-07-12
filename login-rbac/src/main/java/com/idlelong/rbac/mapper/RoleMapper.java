package com.idlelong.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idlelong.rbac.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色(RoleEntity)表数据库访问层
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 根据用户id批量新增角色
     *
     * @param userId  用户id
     * @param roleIds 角色id集合
     * @return 受影响的行数
     */
    int insertRolesByUserId(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

}

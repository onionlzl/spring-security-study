package com.idlelong.rbac.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色-权限关系(RoleResourceEntity)实体类
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("rolere_source")
public class RoleResourceEntity implements Serializable {
    private static final long serialVersionUID = 835575257083503419L;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 资源id
     */
    private Long resourceId;

}


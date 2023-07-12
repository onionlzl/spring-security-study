package com.idlelong.loginsecurity.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色-权限关系(RoleResource)实体类
 *
 * @author idlelong
 * @since 2023-06-12 16:55:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("role_resource")
public class RoleResourceEntity implements Serializable {
    private static final long serialVersionUID = 282937424281341297L;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 资源id
     */
    private Long resourceId;

}


package com.idlelong.rbac.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色(RoleEntity)实体类
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 814426191149692266L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String name;

}


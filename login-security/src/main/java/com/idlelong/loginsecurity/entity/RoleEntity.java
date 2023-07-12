package com.idlelong.loginsecurity.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色(Role)实体类
 *
 * @author idlelong
 * @since 2023-06-12 16:55:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 397456084988426851L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String name;

}


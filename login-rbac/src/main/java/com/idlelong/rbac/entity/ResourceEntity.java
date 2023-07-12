package com.idlelong.rbac.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源(ResourceEntity)实体类
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("resource")
public class ResourceEntity implements Serializable {
    private static final long serialVersionUID = -55483922667922536L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 路径
     */
    private String path;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型。0为菜单，1为接口
     */
    private Integer type;

}


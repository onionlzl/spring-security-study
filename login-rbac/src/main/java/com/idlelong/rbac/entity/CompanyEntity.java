package com.idlelong.rbac.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公司(CompanyEntity)实体类
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("company")
public class CompanyEntity implements Serializable {
    private static final long serialVersionUID = -64242844480589037L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 公司名称
     */
    private String name;

}


package com.idlelong.loginsecurity.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公司(Company)实体类
 *
 * @author idlelong
 * @since 2023-06-12 16:55:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("company")
public class CompanyEntity implements Serializable {
    private static final long serialVersionUID = 143058335846170517L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 公司名称
     */
    private String name;

}


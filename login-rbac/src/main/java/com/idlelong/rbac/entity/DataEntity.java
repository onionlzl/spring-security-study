package com.idlelong.rbac.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据(DataEntity)实体类
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("data")
public class DataEntity implements Serializable {
    private static final long serialVersionUID = 952484646107228256L;
    /**
     * 主键
     */
    private Object id;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 客户电话
     */
    private String customerPhone;
    /**
     * 订单价格
     */
    private Double price;
    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;
    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 公司名称，冗余字段
     */
    private String companyName;

}


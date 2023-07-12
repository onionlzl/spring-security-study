package com.idlelong.rbac.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 数据分页展示数据
 *
 * @author lizhenlong
 * @date 2023/06/06
 */
@Data
public class DataPageVO implements Serializable {
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 客户手机
     */
    private String customerPhone;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 公司名称
     */
    private String companyName;
}
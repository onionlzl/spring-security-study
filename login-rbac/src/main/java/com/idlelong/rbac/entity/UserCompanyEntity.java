package com.idlelong.rbac.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户-公司关系(UserCompanyEntity)实体类
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_company")
public class UserCompanyEntity implements Serializable {
    private static final long serialVersionUID = 406538870538676912L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 公司id
     */
    private Long companyId;

}


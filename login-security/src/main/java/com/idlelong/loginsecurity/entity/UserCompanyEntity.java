package com.idlelong.loginsecurity.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户-公司关系(UserCompany)实体类
 *
 * @author idlelong
 * @since 2023-06-12 16:55:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_company")
public class UserCompanyEntity implements Serializable {
    private static final long serialVersionUID = 994317826289805549L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 公司id
     */
    private Long companyId;

}


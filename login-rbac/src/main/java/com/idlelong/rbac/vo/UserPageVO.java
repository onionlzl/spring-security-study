package com.idlelong.rbac.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户分页信息
 *
 * @author lizhenlong
 * @date 2023/06/05
 */
@Data
public class UserPageVO implements Serializable {

    private Long id;
    private String username;
    private Set<Long> roleIds;
    private Set<Long> companyIds;
}

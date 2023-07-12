package com.idlelong.loginsecurity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户签证官
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@Data
@Accessors(chain = true)
public class UserVO implements Serializable {

    private static final long serialVersionUID = -4808336076123592081L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录认证token
     */
    private String token;
    /**
     * 当前用户的权限资源id集合
     */
    private Set<Long> resourceIds;
}

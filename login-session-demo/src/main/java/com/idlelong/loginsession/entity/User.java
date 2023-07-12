package com.idlelong.loginsession.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户实体
 *
 * @author lizhenlong
 * @date 2023/06/01
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -8511967413838016242L;

    /**
     * 用户名
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;

}

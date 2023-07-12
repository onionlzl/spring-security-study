package com.idlelong.rbac.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录请求
 *
 * @author lizhenlong
 * @date 2023/06/05
 */
@Data
public class LoginReq implements Serializable {

    private static final long serialVersionUID = 1464823638094436631L;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 12, message = "用户名长度为4-12位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 12, message = "密码长度为4-12位")
    private String password;
}

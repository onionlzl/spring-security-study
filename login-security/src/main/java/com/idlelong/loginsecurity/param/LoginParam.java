package com.idlelong.loginsecurity.param;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录参数
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@Data
@Accessors(chain = true)
public class LoginParam implements Serializable {

    private static final long serialVersionUID = 8799014415828711229L;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 12, message = "用户名长度为4-12位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 12, message = "密码长度为4-12位")
    private String password;
}

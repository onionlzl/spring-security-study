package com.idlelong.rbac.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 用户要求
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq implements Serializable {

    @NotNull(message = "用户id不能为空", groups = Update.class)
    private Long id;

    @NotBlank(message = "用户名不能为空", groups = CreateUser.class)
    @Length(min = 4, max = 12, message = "用户名长度为4-12位", groups = CreateUser.class)
    private String username;

    private List<Long> roleIds;

    private List<Long> companyIds;

    public interface Update {}

    public interface CreateUser{}
}

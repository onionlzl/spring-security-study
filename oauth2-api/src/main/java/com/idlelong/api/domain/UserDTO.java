package com.idlelong.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用户dto
 *
 * @author lizhenlong
 * @date 2023/07/13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -8265153514685364205L;

    private Long id;
    private String username;
    private String password;
    private List<String> roles;
}
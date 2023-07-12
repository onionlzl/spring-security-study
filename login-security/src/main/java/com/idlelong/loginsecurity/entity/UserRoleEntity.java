package com.idlelong.loginsecurity.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户-角色关系表(UserRole)实体类
 *
 * @author idlelong
 * @since 2023-06-12 16:55:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_role")
public class UserRoleEntity implements Serializable {
    private static final long serialVersionUID = 305780017760114608L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;

}


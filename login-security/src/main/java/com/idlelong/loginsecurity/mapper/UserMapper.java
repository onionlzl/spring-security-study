package com.idlelong.loginsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idlelong.loginsecurity.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 用户(User)表数据库访问层
 *
 * @author idlelong
 * @since 2023-06-12 16:42:01
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 选择用户名
     *
     * @param username 用户名
     * @return {@link UserEntity}
     */
    UserEntity selectByUsername(@Param("username") String username);
}


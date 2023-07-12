package com.idlelong.loginsecurity.constant;

/**
 * Redis常量
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
public class RedisConstant {

    /**
     * 用户资源key
     */
    public static final String USER_RESOURCES_KEY = "user:resources";
    /**
     * 用户资源到期
     */
    public static final long USER_RESOURCES_EXPIRE = 24 * 60 * 60;
}

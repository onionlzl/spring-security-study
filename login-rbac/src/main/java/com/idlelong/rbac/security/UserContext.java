package com.idlelong.rbac.security;

/**
 * 用户上下文对象，方便在系统中任何地方都能获取用户信息
 *
 * @author lizhenlong
 * @date 2023/06/05
 */
public final class UserContext {

    /**
     * 私有构造方法
     */
    private UserContext(){}

    private static final ThreadLocal<Long> USER = new ThreadLocal<>();

    public static void add(Long id) {
        USER.set(id);
    }

    public static void remove() {
        USER.remove();
    }

    /**
     * @return 当前登录用户的id
     */
    public static Long getCurrentUserId() {
        return USER.get();
    }
}

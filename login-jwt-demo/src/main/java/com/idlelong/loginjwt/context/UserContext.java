package com.idlelong.loginjwt.context;

/**
 * 用户上下文
 *
 * @author lizhenlong
 * @date 2023/06/02
 */
public final class UserContext {

    private static final ThreadLocal<String> user = new ThreadLocal<String>();

    public static void add(String userName) {
        user.set(userName);
    }

    public static void remove() {
        user.remove();
    }

    /**
     * 获得当前用户名
     *
     * @return 当前登录用户的用户名
     */
    public static String getCurrentUserName() {
        return user.get();
    }
}
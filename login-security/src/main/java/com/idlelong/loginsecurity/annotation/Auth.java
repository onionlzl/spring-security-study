package com.idlelong.loginsecurity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解，用于标注需要权限管理的接口
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Auth {

    /**
     * 权限id，模块id + 方法id需要唯一
     *
     * @return long
     */
    long id();

    /**
     * 名字
     *
     * @return {@link String}
     */
    String name();
}

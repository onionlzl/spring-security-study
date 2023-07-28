package com.idlelong.gateway.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 枚举了一些常用API操作码
 *
 * @author lizhenlong
 * @date 2023/05/31
 */
@AllArgsConstructor
@Getter
public enum ResultCode implements IErrorCode {

    /**
     * 互联网公用-响应状态码
     */
    SUCCESS("200", "操作成功"),
    FAILED("500", "操作失败"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限"),

    /**
     * BA 基础业务异常状态码
     */
    BIZ_VALIDATE_FAILED("BIZ-001", "参数检验失败"),
    ;
    private String code;

    private String message;

    public static final List<ResultCode> COMMON_STATUS_ALL = Collections.unmodifiableList(
            Arrays.asList(SUCCESS, FAILED, UNAUTHORIZED, FORBIDDEN
            ));
}

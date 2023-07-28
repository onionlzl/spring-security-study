package com.idlelong.gateway.api;

/**
 * ierror代码
 *
 * @author lizhenlong
 * @date 2023/05/31
 */
public interface IErrorCode {
    /**
     * 获取代码
     *
     * @return {@link String}
     */
    String getCode();

    /**
     * 得到消息
     *
     * @return {@link String}
     */
    String getMessage();
}

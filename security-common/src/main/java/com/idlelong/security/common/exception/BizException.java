package com.idlelong.security.common.exception;

import com.idlelong.security.common.api.IErrorCode;

/**
 * 业务异常
 *
 * @author lizhenlong
 * @date 2023/06/01
 */
public class BizException extends RuntimeException{

    private IErrorCode errorCode;

    public BizException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}

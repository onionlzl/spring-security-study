package com.idlelong.security.common.config;

import com.idlelong.security.common.api.CommonResult;
import com.idlelong.security.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 全局异常处理
 *
 * @author lizhenlong
 * @date 2023/06/01
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {


    @InitBinder
    public void handleInitBinder(WebDataBinder dataBinder){
        dataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }

    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public CommonResult<String> handle(BizException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public CommonResult<String> handleValidException(Exception e) {
        StringBuilder errorMessage = new StringBuilder();
        if (e instanceof BindException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            bindingResult.getAllErrors()
                    .forEach(a -> errorMessage.append(((FieldError) a).getField() + ": " + a.getDefaultMessage()));
        } else if (e instanceof ConstraintViolationException) {
            if (e.getMessage() != null) {
                errorMessage.append((e.getMessage()));
            }
        } else {
            errorMessage.append("无效参数");
        }
        return CommonResult.validateFailed(errorMessage.toString());
    }

    @ExceptionHandler({Throwable.class})
    public CommonResult<String> handle(Throwable ex) {
        log.error("未知异常捕获msg: {}", ex.getMessage(), ex);
        return CommonResult.failed("该操作异常！");
    }
}

package com.idlelong.loginsecurity.security.compoent;

import cn.hutool.json.JSONUtil;
import com.idlelong.security.common.api.CommonResult;
import com.idlelong.security.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 授权错误处理器
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@Slf4j
public class MyDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("权限异常:{}",accessDeniedException.getMessage());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        CommonResult<String> resultVO = CommonResult.failed(ResultCode.FORBIDDEN);
        out.write(JSONUtil.toJsonStr(resultVO));
        out.flush();
        out.close();
    }
}

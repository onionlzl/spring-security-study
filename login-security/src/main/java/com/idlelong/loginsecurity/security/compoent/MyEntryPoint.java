package com.idlelong.loginsecurity.security.compoent;

import cn.hutool.json.JSONUtil;
import com.idlelong.security.common.api.CommonResult;
import com.idlelong.security.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证错误处理器
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@Slf4j
public class MyEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("认证异常: {}",authException.getMessage());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        CommonResult<String> resultVO = CommonResult.failed(ResultCode.UNAUTHORIZED);
        out.write(JSONUtil.toJsonStr(resultVO));
        out.flush();
        out.close();
    }
}

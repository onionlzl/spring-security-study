package com.idlelong.rbac.security;

import com.idlelong.rbac.constant.BizConstant.*;
import com.idlelong.rbac.entity.ResourceEntity;
import com.idlelong.rbac.service.ResourceService;
import com.idlelong.security.common.api.ResultCode;
import com.idlelong.security.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 身份验证拦截器
 *
 * @author lizhenlong
 * @date 2023/06/05
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private ResourceService resourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是静态资源，直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 获取请求的最佳匹配路径，这里的意思就是我之前数据演示的/API/user/test/{id}路径参数
        // 如果用uri判断的话就是/API/user/test/100，就和路径参数匹配不上了，所以要用这种方式获得
        String pattern = (String) request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        // 将请求方式（GET、POST等）和请求路径用 : 拼接起来，等下好进行判断。最终拼成字符串的就像这样：DELETE:/API/user
        String path = request.getMethod() + ":" + pattern;

        // 拿到所有权限路径 和 当前用户拥有的权限路径（注意注意：这里只是为了演示才走的数据库，实际开发中一定要将权限数据存到缓存中查询）
        Set<String> allPaths = resourceService.queryAll().stream().map(ResourceEntity::getPath).collect(Collectors.toSet());
        List<ResourceEntity> resource = resourceService.getResourcesByUserId(UserContext.getCurrentUserId());
        Set<String> userPaths = resource.stream()
                .filter(e -> ResouceType.INTER_FACE_TYPE.getCode().equals(e.getType()))
                .map(ResourceEntity::getPath)
                .collect(Collectors.toSet());
        // 第一个判断：所有权限路径中包含该接口，才代表该接口需要权限处理，所以这是先决条件，
        // 第二个判断：判断该接口是不是属于当前用户的权限范围，如果不是，则代表该接口用户没有权限
        if (allPaths.contains(path) && !userPaths.contains(path)) {
            // 没有权限则抛出异常
            throw new BizException(ResultCode.FORBIDDEN);
        }
        // 有权限就放行
        return true;
    }

}

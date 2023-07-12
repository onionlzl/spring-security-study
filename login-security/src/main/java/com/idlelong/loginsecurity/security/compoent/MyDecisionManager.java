package com.idlelong.loginsecurity.security.compoent;

import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

/**
 * 授权管理，决定当前用户是否有该请求权限
 *
 * @author lizhenlong
 * @date 2023/06/13
 */
@Slf4j
public class MyDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 如果授权规则为空则代表此URL无需授权就能访问
        if (Collections.isEmpty(configAttributes)) {
            return;
        }
        log.info("---DecisionManager---");
        // 判断授权规则和当前用户所属权限是否匹配
        for (ConfigAttribute ca : configAttributes) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                // 如果匹配上了，代表当前登录用户是有该权限的，直接结束方法
                if (Objects.equals(authority.getAuthority(), ca.getAttribute())) {
                    return;
                }
            }
        }
        // 走到这里就代表没有权限
        throw new AccessDeniedException("抱歉，您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

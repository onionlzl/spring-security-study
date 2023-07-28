package com.idlelong.gateway.filter;


import com.idlelong.gateway.config.IgnoreUrlsConfig;
import com.idlelong.gateway.constant.AuthConstant;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 白名单路径访问时需要移除JWT请求头
 *
 * @author lizhenlong
 * @date 2023/07/12
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        // 白名单路径移除JWT请求头
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            // 如果是白名单匹配路径
            if (pathMatcher.match(ignoreUrl,uri.getPath())){
                // 白名单路径移除JWT请求头
                request = exchange.getRequest().mutate()
                        .header(AuthConstant.AUTHORIZATION_HEAD,"").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}

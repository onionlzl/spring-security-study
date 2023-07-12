package com.idlelong.loginsecurity.security.config;

import com.idlelong.loginsecurity.security.compoent.AuthFilter;
import com.idlelong.loginsecurity.security.compoent.LoginFilter;
import com.idlelong.loginsecurity.security.compoent.MyDeniedHandler;
import com.idlelong.loginsecurity.security.compoent.MyEntryPoint;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

/**
 * 安全配置
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoginFilter loginFilter;

    @Resource
    private AuthFilter authFilter;

    @Resource
    private MyEntryPoint myEntryPoint;

    @Resource
    private MyDeniedHandler myDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf和frameOptions，如果不关闭会影响前端请求接口（这里不展开细讲了，感兴趣的自行了解）
        http.csrf().disable();
        http.headers().frameOptions().disable();
        // 开启跨域以便前端调用接口
        http.cors();

        // 这是配置的关键，决定哪些接口开启防护，哪些接口绕过防护
        http.authorizeRequests()
                // 注意这里，是允许前端跨域联调的一个必要配置
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 指定某些接口不需要通过验证即可访问。像登陆、注册接口肯定是不需要认证的
                .antMatchers("/API/login", "/API/register").permitAll()
                // 这里意思是其它所有接口需要认证才能访问
                .antMatchers("/API/**").authenticated()
                // 指定认证错误处理器
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(myEntryPoint)
                .accessDeniedHandler(myDeniedHandler);

        // 禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 将我们自定义的认证过滤器放在掉默认的认证过滤器之前
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        // 将我们自定义的授权过滤器放在默认的授权过滤器之前
        http.addFilterBefore(authFilter, FilterSecurityInterceptor.class);
    }

}
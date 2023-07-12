package com.idlelong.loginsecurity.security.util;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

/**
 * JwtToken 工具类
 *
 * @author lizhenlong
 * @date 2023/06/05
 */
@Component
@Slf4j
public class JwtTokenUtil {

    /**
     * 秘密密钥
     */
    @Value("${security.jwt.secretKey}")
    private String secretKey;

    /**
     * 过期时间 默认为2 单位小时
     */
    @Value("${security.jwt.expiration.time:2}")
    private long expirationTime;


    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = parse(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 生成JWT
     *
     * @param userName 用户名
     * @return JWT
     */
    public String generate(String userName) {
        // 过期时间
        Date expiryDate = new Date(System.currentTimeMillis() + Duration.ofHours(expirationTime).toMillis());
        return Jwts.builder()
                // 将userId放进JWT
                .setSubject(userName)
                // 设置JWT签发时间
                .setIssuedAt(new Date())
                // 设置过期时间
                .setExpiration(expiryDate)
                // 设置加密算法和秘钥
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * 解析JWT
     *
     * @param token JWT字符串
     * @return 解析成功返回Claims对象，解析失败返回null
     */
    public Claims parse(String token) {
        // 如果是空字符串直接返回null
        if (StrUtil.isBlank(token)) {
            return null;
        }
        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token过期、token非法都会导致解析失败
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            log.error("token解析失败:{}", e.toString());
        }
        return claims;
    }
}

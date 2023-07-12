package com.idlelong.loginjwt.util;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;

/**
 * Jwt工具类
 *
 * @author lizhenlong
 * @date 2023/06/02
 */
@Slf4j
public final class JwtUtil {

    /**
     * 这个秘钥是防止JWT被篡改的关键，随便写什么都好，但决不能泄露
     */
    private final static String SECRET_KEY = "idlelong";

    /**
     * 过期时间目前设置成2小时，这个配置随业务需求而定
     */
    private final static Duration EXPIRATION_TIME = Duration.ofHours(2);

    /**
     * 生成Jwt
     *
     * @param username 用户名
     * @return {@link String}
     */
    public static String generateJwt(String username){
        // 过期时间
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME.toMillis());

        return Jwts.builder()
                // 将username设置房间JWT
                .setSubject(username)
                // 设置签发时间
                .setIssuedAt(new Date())
                // 设置到期时间
                .setExpiration(expiryDate)
                // 设置加密算法和秘钥
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 解析JWT
     *
     * @param token JWT字符串
     * @return 解析成功返回Claims对象，解析失败返回null
     */
    public static Claims parse(String token) {
        // 如果是空字符串直接返回null
        if (StrUtil.isBlank(token)) {
            return null;
        }
        // 这个Claims对象包含了许多属性，比如签发时间、过期时间以及存放的数据等
        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token过期、token非法都会导致解析失败
        try {
            claims = Jwts.parser()
                    // 设置秘钥
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            // 这里应该用日志输出，为了演示方便就直接打印了
            log.error("token解析错,token:{},错误原因:{}",token,e.getMessage());
        }
        return claims;
    }

}

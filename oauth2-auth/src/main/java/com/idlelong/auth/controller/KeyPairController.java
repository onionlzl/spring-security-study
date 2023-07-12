package com.idlelong.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * 密钥对控制器
 *
 * @author lizhenlong
 * @date 2023/07/03
 */
@RestController
@RequestMapping("/rsa")
public class KeyPairController {

    private final KeyPair keyPair;


    public KeyPairController(KeyPair keyPair) {
        this.keyPair = keyPair;
    }


    @GetMapping("/publicKey")
    public Map<String,Object> getKey(){
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}

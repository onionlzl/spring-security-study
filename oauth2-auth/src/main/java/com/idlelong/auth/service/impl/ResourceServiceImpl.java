package com.idlelong.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.idlelong.auth.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源服务impl
 *
 * @author lizhenlong
 * @date 2023/07/03
 */
@Service
public class ResourceServiceImpl {

    /**
     * 人力资源角色映射
     */
    private Map<String, List<String>> resourceRolesMap;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @PostConstruct
    public void initData(){
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", CollUtil.toList("ADMIN", "TEST"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }


}

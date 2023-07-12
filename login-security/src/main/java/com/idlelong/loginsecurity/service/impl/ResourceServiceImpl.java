package com.idlelong.loginsecurity.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.idlelong.loginsecurity.constant.BizConstant;
import com.idlelong.loginsecurity.constant.RedisConstant;
import com.idlelong.loginsecurity.entity.ResourceEntity;
import com.idlelong.loginsecurity.mapper.ResourceMapper;
import com.idlelong.loginsecurity.service.ResourceService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 资源服务impl
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Resource
    private ResourceMapper resourceMapper;

    @Override
    public Set<Long> getIdsByUserId(Long id) {
        String redisKey = RedisConstant.USER_RESOURCES_KEY+":"+id;
        String resourcesStr = redisTemplate.opsForValue().get(redisKey);
        Set<Long> resourceIds = new HashSet<>();
        if (StrUtil.isNotBlank(resourcesStr) && JSONUtil.isJsonArray(resourcesStr)) {
           resourceIds = CollUtil.newHashSet(false,JSONUtil.toList(resourcesStr,Long.class)) ;
           return resourceIds;
        }
        resourceIds = resourceMapper.selectIdsByUserId(id);
        redisTemplate.opsForValue().set(redisKey, JSONUtil.toJsonStr(resourceIds), RedisConstant.USER_RESOURCES_EXPIRE, TimeUnit.SECONDS);
        return resourceIds;
    }

    @Override
    public void deleteResourceByType(BizConstant.ResouceType resouceType) {
        if (resouceType == null){
            return;
        }
        resourceMapper.deleteByType(resouceType.getCode());
    }

    @Override
    public void batchInsert(List<ResourceEntity> resourceEntityList) {
        if (CollUtil.isEmpty(resourceEntityList)) {
            return;
        }
        resourceMapper.insertBatch(resourceEntityList);
    }
}

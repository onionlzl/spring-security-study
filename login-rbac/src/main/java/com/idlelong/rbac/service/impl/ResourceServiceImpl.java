package com.idlelong.rbac.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.idlelong.rbac.constant.BizConstant;
import com.idlelong.rbac.entity.ResourceEntity;
import com.idlelong.rbac.mapper.ResourceMapper;
import com.idlelong.rbac.service.ResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 资源服务impl
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceMapper resourceMapper;

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

    @Override
    public List<ResourceEntity> queryAll() {
        List<ResourceEntity> resourceEntityList = new ArrayList<>();
        resourceEntityList = resourceMapper.selectList(Wrappers.emptyWrapper());
        return resourceEntityList;
    }

    @Override
    public List<ResourceEntity> getResourcesByUserId(Long userId) {
        List<ResourceEntity> resourceEntityList = new ArrayList<>();
        resourceEntityList = resourceMapper.selectListByUserId(userId);
        return resourceEntityList;
    }

    @Override
    public Set<Long> getIdsByUserId(Long userId) {
        return resourceMapper.selectIdsByUserId(userId);
    }
}

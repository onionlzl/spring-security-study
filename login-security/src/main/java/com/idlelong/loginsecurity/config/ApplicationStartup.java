package com.idlelong.loginsecurity.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.idlelong.loginsecurity.annotation.Auth;
import com.idlelong.loginsecurity.constant.BizConstant.ResouceType;
import com.idlelong.loginsecurity.entity.ResourceEntity;
import com.idlelong.loginsecurity.security.compoent.MySecurityMetadataSource;
import com.idlelong.loginsecurity.service.ResourceService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 应用程序启动时执行
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
@Component
public class ApplicationStartup implements ApplicationRunner {

    private final RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

    private final ResourceService resourceService;

    public ApplicationStartup(RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping, ResourceService resourceService) {
        this.requestMappingInfoHandlerMapping = requestMappingInfoHandlerMapping;
        this.resourceService = resourceService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1、扫描并获取所有需要权限处理的接口资源
        List<ResourceEntity> resourceEntityList = getAuthResources();
        // 2、先删除所有操作类型的权限资源，再新增资源，以实现全量更新
        resourceService.deleteResourceByType(ResouceType.INTER_FACE_TYPE);
        // 如果权限资源为空，就不用走后续数据插入步骤
        if (CollUtil.isEmpty(resourceEntityList)) {
            return;
        }
        // 将权限资源给放到权限缓存里
        MySecurityMetadataSource.getRESOURCES().addAll(resourceEntityList);
        // 将资源数据批量添加到数据库
        resourceService.batchInsert(resourceEntityList);
    }

    /**
     * 扫描并返回所有需要权限处理的接口资源
     *
     * @return {@link List}<{@link ResourceEntity}>
     */
    private List<ResourceEntity> getAuthResources() {
        // 保证有序
        List<ResourceEntity> resourceEntityList = new LinkedList<>();
        // 拿到所有接口信息，并开始遍历
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMapping.getHandlerMethods();
        if (MapUtil.isNotEmpty(handlerMethods)){
            handlerMethods.forEach((info,handlerMethod)->{
                // 拿到类(模块)上的权限注解
                Auth moduleAuth = handlerMethod.getBeanType().getAnnotation(Auth.class);
                // 拿到接口方法上的权限注解
                Auth methodAuth = handlerMethod.getMethod().getAnnotation(Auth.class);
                // 如果模块注解或方法注解缺失则不进行权限处理
                if (moduleAuth == null || methodAuth == null){
                    return;
                }

                // 拿到该接口方法的请求方式（GET,POST等）
                Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
                // 如果一个接口标记了多个请求方式，权限id是无法失败的，不进行处理
                if (methods == null || methods.size() != 1) {
                    return;
                }

                // 将请求方式和路径用`:`拼接起来，用于区分接口，比如：GET:/user/{id}、POST:/user/{id}
                assert info.getPathPatternsCondition() != null;
                String path = methods.toArray()[0] + ":" + info.getPathPatternsCondition().getPatterns().toArray()[0];
                ResourceEntity resourceEntity = ResourceEntity.builder()
                        .type(ResouceType.INTER_FACE_TYPE.getCode())
                        .path(path)
                        .name(methodAuth.name())
                        .id(moduleAuth.id() + methodAuth.id())
                        .build();
                resourceEntityList.add(resourceEntity);
            });
        }
        return resourceEntityList;
    }
}

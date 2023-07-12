package com.idlelong.rbac.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idlelong.rbac.entity.UserEntity;
import com.idlelong.rbac.mapper.ResourceMapper;
import com.idlelong.rbac.mapper.UserMapper;
import com.idlelong.rbac.req.LoginReq;
import com.idlelong.rbac.req.UserPageReq;
import com.idlelong.rbac.req.UserReq;
import com.idlelong.rbac.security.JwtManager;
import com.idlelong.rbac.security.UserContext;
import com.idlelong.rbac.service.ResourceService;
import com.idlelong.rbac.service.RoleService;
import com.idlelong.rbac.service.UserService;
import com.idlelong.rbac.vo.UserPageVO;
import com.idlelong.rbac.vo.UserVO;
import com.idlelong.security.common.api.BaseComonPageReq;
import com.idlelong.security.common.api.CommonPage;
import com.idlelong.security.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户服务impl
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
@Service
public class UserServiceImpl implements UserService {

    private final ResourceService resourceService;

    private final RoleService roleService;

    @Autowired
    private JwtManager jwtManager;

    @Resource
    private ResourceMapper resourceMapper;

    @Resource
    private UserMapper userMapper;

    public UserServiceImpl(ResourceService resourceService, RoleService roleService) {
        this.resourceService = resourceService;
        this.roleService = roleService;
    }


    @Override
    public UserVO login(LoginReq loginReq) {
        // 根据前端传递过来的账号密码从数据库中查询用数据
        UserEntity userEntity = userMapper.selectByLogin(loginReq.getUsername(),loginReq.getPassword());
        if (userEntity == null) {
            throw new ApiException("账号或密码错误");
        }
        UserVO userVO = new UserVO();
        resourceService.getIdsByUserId(userEntity.getId());
        userVO.setId(userEntity.getId()).setUsername(loginReq.getUsername())
                .setToken(jwtManager.generate(userVO.getId()))
                .setResourceIds(resourceService.getIdsByUserId(userEntity.getId()));
        // 返回该用户的权限路径集合
        return userVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(UserReq param) {
        LambdaQueryWrapper<UserEntity> queryWrapper = Wrappers.<UserEntity>lambdaQuery()
                .eq(UserEntity::getUsername,param.getUsername());
        List<UserEntity> userEntities = userMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(userEntities)){
            throw new BizException("用户名重复");
        }
        // 密码默认和账号名一致
        UserEntity user = UserEntity.builder()
                .username(param.getUsername()).password(param.getUsername())
                .build();

        userMapper.insert(user);
        // 再新增权限数据
        roleService.insertRolesByUserId(user.getId(), param.getRoleIds());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByUserIds(List<Long> userIds) {
        // 删除用户下的所属角色
        roleService.removeByUserIds(userIds);
        // 删除用户
        userMapper.deleteBatchIds(userIds);
    }

    @Override
    public void update(UserReq param) {

    }

    @Override
    public CommonPage<UserPageVO> selectPage(UserPageReq userPageReq) {
        Page<UserPageVO> pageReq = BaseComonPageReq.<UserPageVO>restPageReq(userPageReq);
        // 设置分页参数
        // 不显示用户1（超级管理账号）也不显示当前登录用户
        QueryWrapper<UserPageVO> queryWrapper = new QueryWrapper();
        queryWrapper.ne("id", 1).ne("id", UserContext.getCurrentUserId())
                // 模糊查询
                .likeRight(StrUtil.isNotBlank(userPageReq.getUsername()),"username",userPageReq.getUsername());
        // 获取分页列表
        IPage<UserPageVO> pages = userMapper.selectPage(pageReq, queryWrapper);
        return CommonPage.restPage(pages);
    }
}

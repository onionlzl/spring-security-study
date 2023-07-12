package com.idlelong.rbac.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idlelong.rbac.entity.UserEntity;
import com.idlelong.rbac.vo.UserPageVO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户(UserEntity)表数据库访问层
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 查询通过登录信息
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link UserEntity}
     */
    UserEntity selectByLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 查询用户分页信息
     *
     * @param wrapper 包装器
     * @param pageReq 页面请求
     * @return {@link IPage}<{@link UserPageVO}>
     */
    IPage<UserPageVO> selectPage(Page<UserPageVO> pageReq, @Param(Constants.WRAPPER) QueryWrapper<UserPageVO> wrapper);
}


package com.idlelong.loginsecurity.service.impl;

import com.idlelong.loginsecurity.entity.UserEntity;
import com.idlelong.loginsecurity.mapper.UserMapper;
import com.idlelong.loginsecurity.param.LoginParam;
import com.idlelong.loginsecurity.security.util.JwtTokenUtil;
import com.idlelong.loginsecurity.security.UserDetail;
import com.idlelong.loginsecurity.service.ResourceService;
import com.idlelong.loginsecurity.service.UserService;
import com.idlelong.loginsecurity.vo.UserVO;
import com.idlelong.security.common.exception.BizException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户服务impl
 *
 * @author lizhenlong
 * @date 2023/06/12
 */
@Service
public class UserServiceImpl implements UserService {


    @Resource
    private ResourceService resourceService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 先调用DAO层查询用户实体对象
        UserEntity user = userMapper.selectByUsername(username);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (user == null) {
            throw new UsernameNotFoundException("没有找到该用户");
        }
        // 查询权限id
        Set<SimpleGrantedAuthority> authorities = resourceService.getIdsByUserId(user.getId())
                .stream()
                .map(String::valueOf)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        // 走到这代表查询到了实体对象，返回我们自定义的UserDetail对象（这里权限暂时放个空集合，后面我会讲解）
        return new UserDetail(user, authorities);
    }

    @Override
    public UserVO login(LoginParam param) {
        // 1、根据用户名查询用户实体对象
        UserEntity userEntity = userMapper.selectByUsername(param.getUsername());
        // 如果用户或者密码校验失败则抛出异常
        if (userEntity == null ){
            throw new BizException("账号不存在");
        }
        if (!passwordEncoder.matches(param.getPassword(), userEntity.getPassword())) {
            throw new BizException("密码错误");
        }
        UserVO userVO = new UserVO();
        userVO.setId(userEntity.getId())
                .setUsername(userEntity.getUsername())
                // 生成token  2
                .setToken(jwtTokenUtil.generate(userEntity.getUsername()));
        return userVO;
    }


}

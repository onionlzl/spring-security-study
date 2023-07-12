package com.idlelong.service.impl;

import cn.hutool.json.JSONUtil;
import com.idlelong.domain.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务impl
 *
 * @author lizhenlong
 * @date 2023/06/14
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static List<User> userList;

    @Resource
    private PasswordEncoder passwordEncoder;

    private static final String USER_KEY = "init:user";

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void initData() throws IOException {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();
        userList.add(new User("idlelong", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("onion", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new User("clay", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        redisTemplate.opsForValue().set(USER_KEY,new ObjectMapper().writeValueAsString(userList));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = new ArrayList<>();
        String userListStr = redisTemplate.opsForValue().get(USER_KEY);
        if (JSONUtil.isJsonArray(userListStr)) {
            userList = JSONUtil.toList(userListStr, User.class);
        }
        List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }
}

package com.gxa.modules.login.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.mapper.UserMapper;
import com.gxa.modules.login.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User queryByUsername(String username) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("user_name", username));
        return user;
    }
}

package com.gxa.modules.login.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.mapper.UserMapper;
import com.gxa.modules.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryById(String openId) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
        return user;
    }

    @Override
    public User queryByPhoneNum(String phoneNum) {
        User user =null;
        user = baseMapper.selectOne(new QueryWrapper<User>().eq("phone_number", phoneNum));
        Integer userId = userMapper.userId(phoneNum);
        user.setId(userId);
        return user;
    }

    @Override
    public void add(User user) {
        baseMapper.insert(user);
    }
}

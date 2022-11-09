package com.gxa.modules.sys.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.mapper.UserMapper;
import com.gxa.modules.sys.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User queryByUsername(String username) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("user_name", username));
        return user;
    }

    @Override
    public List<User> queryUser() {
        List<User> list = baseMapper.selectList(null);
        return list;
    }
}

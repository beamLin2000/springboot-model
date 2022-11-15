package com.gxa.modules.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    User queryById(String openId);
    User queryByPhoneNum(String phoneNum);
    void add(User user);
}

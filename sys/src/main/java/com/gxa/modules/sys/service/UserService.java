package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    User queryByUsername(String username);
    List<User> queryUser();
}

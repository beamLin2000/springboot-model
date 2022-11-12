package com.gxa.modules.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.login.dto.Role;

import java.util.List;
import java.util.Map;

public interface RoleService extends IService<Role> {
    List<Role> queryAll(Map<String,Object> params);
    void del(String name);
}

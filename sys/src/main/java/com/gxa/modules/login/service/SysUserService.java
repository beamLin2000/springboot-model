package com.gxa.modules.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.login.dto.User;
import com.gxa.modules.login.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    SysUser queryByUsername(String username);
    User queryByRealname(String username);
    void updateUser(SysUser sysUser);
    String queryUrl(String username);



}

package com.gxa.modules.login.service;

import com.gxa.common.utils.Result;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserTokenService {
    Result createToken(User user);
    Result createToken(SysUser sysUser);

    User validateToken(String token);
    SysUser validateSysUserToken(String token);
}

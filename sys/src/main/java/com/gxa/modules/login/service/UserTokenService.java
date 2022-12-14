package com.gxa.modules.login.service;

import com.gxa.common.utils.Result;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserTokenService {
    void createToken(String phone,String captcha);
    Result createToken(SysUser sysUser);
    Result createToken(User user);
    Result createWeiToken(User user);
    Result createSessionId(String code,String openId);
    User validateToken(String token);
    SysUser validateSysUserToken(String token);
    User validateUserToken(String token);
    String validateCaptcha(String phone);
}

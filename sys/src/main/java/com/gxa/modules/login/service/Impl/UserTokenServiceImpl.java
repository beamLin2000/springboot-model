package com.gxa.modules.login.service.Impl;

import com.gxa.common.utils.Base64Utils;
import com.gxa.common.utils.RedisUtils;
import com.gxa.common.utils.Result;
import com.gxa.common.utils.TokenGenerator;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.redis.SysUserRedis;
import com.gxa.modules.login.service.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserTokenServiceImpl implements UserTokenService {
    @Autowired
    private SysUserRedis sysUserRedis;
    @Override
    public void createToken(String phone,String captcha) {
        sysUserRedis.addToken(phone, captcha);
    }
    @Override
    public Result createToken(User user) {
        String token = TokenGenerator.generateValue(user.getPhoneNumber());
        String encodeToken = Base64Utils.encode(user.getPhoneNumber());
        sysUserRedis.addUserToken(token,user);
        Map map = new HashMap();
        map.put("token",encodeToken);
        return new Result().ok(map);
    }
    @Override
    public Result createToken(SysUser sysUser) {
        String token = TokenGenerator.generateValue(sysUser.getUsername());
        String str = sysUser.getUsername()+":"+token;
        String encodeToken = Base64Utils.encode(str);
        sysUserRedis.addSysToken(token,sysUser);
        Map map = new HashMap();
        map.put("token",encodeToken);

        return new Result().ok(map);
    }
    @Override
    public User validateToken(String token) {
        String decodeToken = Base64Utils.decode(token);
        log.info(decodeToken);
        User user = sysUserRedis.getUserByToken(decodeToken);
        return user;
    }

    @Override
    public SysUser validateSysUserToken(String token) {
        String decodeToken = Base64Utils.decode(token);
        log.info(decodeToken);
        return sysUserRedis.getSysUserByToken(decodeToken);
    }
    @Override
    public String validateCaptcha(String phone) {
        return sysUserRedis.getCaptcha(phone);
    }
}

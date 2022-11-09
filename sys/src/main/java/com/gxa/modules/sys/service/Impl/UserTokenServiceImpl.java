package com.gxa.modules.sys.service.Impl;

import com.gxa.common.utils.Base64Utils;
import com.gxa.common.utils.Result;
import com.gxa.common.utils.TokenGenerator;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.redis.SysUserRedis;
import com.gxa.modules.sys.service.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@Slf4j
public class UserTokenServiceImpl implements UserTokenService {
    @Autowired
    private SysUserRedis sysUserRedis;
    @Override
    public Result createToken(User user) {
        String token = TokenGenerator.generateValue(user.getUsername());
        String str = user.getUsername()+":"+token;
        String encodeToken = Base64Utils.encode(str);
        sysUserRedis.addToken(token,user);
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
}

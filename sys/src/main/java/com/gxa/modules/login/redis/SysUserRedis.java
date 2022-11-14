package com.gxa.modules.login.redis;

import com.gxa.common.utils.JsonUtils;
import com.gxa.common.utils.RedisKeys;
import com.gxa.common.utils.RedisUtils;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SysUserRedis {

    private RedisUtils redisUtils;

    public void addToken(String token, User user){

        //token --- user
        //1111--user
        /**
         * sys:user:username:1111  ---user
         * sys:user:username:2222 ----user
         * sys:user:username:3333 ----user
         */
        this.redisUtils.set(RedisKeys.getSysUserTokenKey(user.getUsername(),token),user);
    }

    public User getUserByToken(String token){
        String userJsonStr = this.redisUtils.get(RedisKeys.getSysUserTokenKey(token));
        User user = JsonUtils.parseObject(userJsonStr, User.class);

        return user;
    }
    public void addSysToken(String token, SysUser sysUser){

        //token --- user
        //1111--user
        /**
         * sys:user:username:1111  ---user
         * sys:user:username:2222 ----user
         * sys:user:username:3333 ----user
         */
        this.redisUtils.set(RedisKeys.getSysUserTokenKey(sysUser.getUsername(),token),sysUser);
    }

    public SysUser getSysUserByToken(String token){
        String userJsonStr = this.redisUtils.get(RedisKeys.getSysUserTokenKey(token));
        SysUser sysUser = JsonUtils.parseObject(userJsonStr, SysUser.class);

        return sysUser;
    }
}

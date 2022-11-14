package com.gxa.modules.login.oauth2;

import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.service.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class OAuth2Realm extends AuthorizingRealm {
//    @Autowired
//    private ShiroService shiroService;

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        //从redis中获取出来
        log.info(accessToken);
        User user = userTokenService.validateToken(accessToken);
        System.out.println(user);
        if(user == null){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)principals.getPrimaryPrincipal();

        Set<String> perms = new HashSet<>();
        //调用数据库 或者从redis中获取该用户的权限
        //我们这里模拟具有权限

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(perms);

        return simpleAuthorizationInfo;
    }


}
 

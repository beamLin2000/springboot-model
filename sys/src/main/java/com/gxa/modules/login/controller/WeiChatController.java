package com.gxa.modules.login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.RedisUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.login.dto.WeiChat;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.service.UserService;
import com.gxa.modules.login.service.UserTokenService;
import com.gxa.modules.login.utils.WechatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "微信接口")
public class WeiChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @PostMapping("/wx/login")
    @ApiOperation("调用微信登录接口")
    public Result user_login(@RequestBody WeiChat weiChat) {
        String openid ="";
        Result result = null;
        Result sessionId =null;
        Map map = new HashMap<>();
        if (!StringUtils.isBlank(weiChat.getSessionId())){
            openid = new RedisUtils().get(weiChat.getSessionId());
            if (StringUtils.isBlank(openid)){
                return new Result().error(ErrorCode.LOGIN_EXPIRED,"登录过期，请重新授权登录");
            }
            User user = this.userService.queryById(openid);
            result = this.userTokenService.createWeiToken(user);
            sessionId = this.userTokenService.createSessionId(openid);
            map.put("data",user);
            map.put("token",result.getData());
            map.put("sessionId",sessionId);
            return new Result().ok(map);
        }
        if (StringUtils.isBlank(weiChat.getCode())){
            return new Result().error("请输入code及相关信息");
        }
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(weiChat.getRawData());
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(weiChat.getCode());
        // 3.接收微信接口服务 获取返回的参数
        openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(weiChat.getRawData() + sessionKey);
        if (!weiChat.getSignature().equals(signature2)) {
            return new Result().error(ErrorCode.INTERNAL_SERVER_ERROR,"签名校验失败");
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；
//        LambdaQueryWrapper<NoseUserForm> lqw = Wrappers.lambdaQuery();
//        lqw.eq(NoseUserForm::getOpenId, openid)
        User u = this.userService.queryById(openid);
//        User user = userService.getOne(lqw);
//        if (user == null) {
//            // 用户信息入库
//            String nickName = rawDataJson.getString("nickName");
//            String avatarUrl = rawDataJson.getString("avatarUrl");
//            user = new User();
//            user.setOpenId(openid);
//            user.setAvatar(avatarUrl);
//            user.setNickName(nickName);
//            userService.save(user);
//        }
//        return R.ok().data(user);
        User user = new User();
        if(u == null){
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            user.setOpenId(openid);
            user.setAvatar(avatarUrl);
            user.setUsername(nickName);
            userService.add(user);
            result = this.userTokenService.createWeiToken(user);
            sessionId = this.userTokenService.createSessionId(openid);
            map.put("data",user);
            map.put("token",result.getData());
            map.put("sessionId",sessionId);
            return new Result().ok(map);
        }
        result = this.userTokenService.createWeiToken(user);
        sessionId = this.userTokenService.createSessionId(openid);
        map.put("data",u);
        map.put("token",result.getData());
        map.put("sessionId",sessionId);
        return new Result().ok(map);
    }

}

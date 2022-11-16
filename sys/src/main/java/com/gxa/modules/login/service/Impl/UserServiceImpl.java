package com.gxa.modules.login.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.RedisUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.login.dto.WeiChat;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.mapper.UserMapper;
import com.gxa.modules.login.service.UserService;
import com.gxa.modules.login.service.UserTokenService;
import com.gxa.modules.login.utils.AesCbcUtil;
import com.gxa.modules.login.utils.AuthUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenService userTokenService;
    @Override
    public User queryById(String openId) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
        return user;
    }

    @Override
    public User queryByPhoneNum(String phoneNum) {
        User user= null;
        user = baseMapper.selectOne(new QueryWrapper<User>().eq("phone_number", phoneNum));
        Integer userId = userMapper.userId(phoneNum);
        user.setId(userId);
        return user;
    }

    @Override
    public void add(User user) {
        baseMapper.insert(user);
    }

    /**
     * @throws
     * @title weChatLogin
     * @description 微信授权登录
     * @author Kuangzc
     * @updateTime 2019-9-12 16:00:51
     */
    @Override
    public Result weChatLogin (WeiChat weiChat) throws Exception {
        Result result = null;
        Result sessionId =null;
        if (!StringUtils.isBlank(weiChat.getSessionId())){
            String openid = new RedisUtils().get(weiChat.getSessionId());
            if (StringUtils.isBlank(openid)){
                return new Result().error(ErrorCode.LOGIN_EXPIRED,"登录过期，请重新授权登录");
            }
            User user = this.queryById(openid);
            result = this.userTokenService.createWeiToken(user);
            sessionId = this.userTokenService.createSessionId(weiChat.getCode(),openid);
            HashMap map = new HashMap();
            map.put("data",user);
            map.put("token",result.getData());
            map.put("sessionId",sessionId);
            return new Result().ok(map);
        }
            //通过第一步获得的code获取微信授权信息
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + AuthUtil.APPID + "&secret="
                    + AuthUtil.APPSECRET + "&js_code=" + weiChat.getCode() + "&grant_type=authorization_code";
            JSONObject jsonObject = AuthUtil.doGetJson(url);
            String openid = jsonObject.getString("openid");
            String session_key = jsonObject.getString("session_key");
            String decrypt = AesCbcUtil.decrypt(weiChat.getData(), session_key, weiChat.getIv(), "utf-8");
            JSONObject userInfo = JSONObject.parseObject(decrypt);

            //成功获取授权,以下部分为业务逻辑处理了，根据个人业务需求写就可以了
            if (userInfo != null && openid != null) {
                String nickname = userInfo.getString("nickName");
                String avatarUrl = userInfo.getString("avatarUrl");
                String city = userInfo.getString("city");
                Integer gender = Integer.parseInt(userInfo.getString("gender"));
                String province = userInfo.getString("province");
//                headimgurl = headimgurl.replace("\\", "");
                //根据openid查询时候有用户信息
                User u = null;
                u = queryById(openid);
                if (Objects.isNull(u)) {
                    User user = new User();
                    user.setOpenId(openid);
                    user.setCity(city);
                    user.setUsername(nickname);
                    user.setHeadPortrait(avatarUrl);
                    user.setProvince(province);
                    user.setGender(gender);
                    //没有绑定用户请前往绑定
                    HashMap map = new HashMap();
                    result = this.userTokenService.createWeiToken(user);
                    sessionId = this.userTokenService.createSessionId(weiChat.getCode(),openid);
                    add(user);
                    map.put("data",user);
                    map.put("token",result.getData());
                    map.put("sessionId",sessionId);
                    return new Result().ok(map);
                } else {
                    //已经绑定用户信息直接登录
                    u.setUsername(nickname);
                    u.setHeadPortrait(avatarUrl);
                    HashMap map = new HashMap();
                    result = this.userTokenService.createWeiToken(u);
                    sessionId = this.userTokenService.createSessionId(weiChat.getCode(),openid);
                    userMapper.updateUser(u);
                    map.put("data",u);
                    map.put("token",result.getData());
                    map.put("sessionId",sessionId);
                    return new Result().ok(map);
                }
            } else {
                return new Result().error("登录校验失败");
            }
        }
}

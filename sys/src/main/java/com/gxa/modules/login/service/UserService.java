package com.gxa.modules.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.Result;
import com.gxa.modules.login.dto.WeiChat;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {
    User queryById(String openId);
    User queryByPhoneNum(String phoneNum);
    void add(User user);
    /**
     * @throws
     * @title weChatLogin
     * @description 微信授权登录
     * @author Kuangzc
     * @updateTime 2019-9-12 16:00:51
     */
    Result weChatLogin( WeiChat weiChat) throws Exception;

}

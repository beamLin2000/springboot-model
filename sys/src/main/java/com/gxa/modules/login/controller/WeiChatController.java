package com.gxa.modules.login.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.login.dto.WeiChat;
import com.gxa.modules.login.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "微信接口")
public class WeiChatController {

    @Autowired
    private UserService userService;
    @ApiOperation(value = "微信授权登录")
    @PostMapping("/weChatLogin")
    protected Result weChatLogin(@RequestBody WeiChat weiChat) throws Exception {
        return userService.weChatLogin(weiChat);
    }
}

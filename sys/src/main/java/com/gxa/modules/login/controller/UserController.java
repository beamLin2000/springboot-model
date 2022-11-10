package com.gxa.modules.login.controller;

import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.Result;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.form.UserForm;
import com.gxa.modules.login.service.UserService;
import com.gxa.modules.login.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserTokenService userTokenService;
    @ApiOperation(value="用户登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody UserForm userForm){
        //1、拿着用户名去 查询用户信息
        User user = this.userService.queryByUsername(userForm.getUsername());
        if(user == null){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }
        //2、通过明文加密  对比  密文 是否一致
        String pwd = new SimpleHash("MD5", userForm.getPassword(), user.getSalt(), 1024).toString();
        //3、不一致      返回Result.error()
        if(!pwd.equals(user.getPwd())){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }
        //4、一致     生成token 保存redis中 返回Result.ok()
        Result result = this.userTokenService.createToken(user);
        Map map = new HashMap();
        map.put("token",result.getData());
        return new Result().ok(map);
    }
}

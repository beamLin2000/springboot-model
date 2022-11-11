package com.gxa.modules.sys.controller;

import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.form.UserForm;
import com.gxa.modules.sys.service.UserService;
import com.gxa.modules.sys.service.UserTokenService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserTokenService userTokenService;
    @PostMapping("/sys/login")
    public Result login(@RequestBody UserForm userForm, HttpServletRequest request){
       User user = this.userService.queryByUsername(userForm.getUsername());
        if (user==null){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码错误");
        }
        String pwd = new SimpleHash("MD5", userForm.getPassword(), user.getSalt(), 1024).toString();
        if (!pwd.equals(user.getPwd())){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码错误");
        }
        Result result = userTokenService.createToken(user);
        return result;
    }
    @PostMapping("/save")
    public Result userList(@RequestBody User user){
        this.userService.save(user);
        return new Result().ok();
    }
}

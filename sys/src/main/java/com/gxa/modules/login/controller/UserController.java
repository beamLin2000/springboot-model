package com.gxa.modules.login.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.common.utils.AssertUtils;
import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.Result;
import com.gxa.common.validator.ValidatorUtils;
import com.gxa.common.validator.group.UpdateGroup;
import com.gxa.modules.login.entity.Menu;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.form.UserForm;
import com.gxa.modules.login.service.SysUserService;
import com.gxa.modules.login.service.UserService;
import com.gxa.modules.login.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserTokenService userTokenService;
    @ApiOperation(value="前端用户登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody UserForm userForm){
        AssertUtils.isNull(userForm,"账号或密码不能为空");
        //1、拿着用户名去 查询用户信息
        User user = this.userService.queryByUsername(userForm.getUsername());
        if(user == null){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }
        //2、通过明文加密  对比  密文 是否一致
        String pwd = new SimpleHash("MD5", userForm.getPassword(), user.getSalt(), 1024).toString();
        //3、不一致      返回Result.error()
        if(!pwd.equals(user.getPassword())){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }
        //4、一致     生成token 保存redis中 返回Result.ok()
        Result result = this.userTokenService.createToken(user);
        Map map = new HashMap();
        map.put("token",result.getData());
        return new Result().ok(map);
    }
    @ApiOperation(value="后台登录接口")
    @PostMapping("/sys/login")
    public Result sysLogin(@RequestBody UserForm userForm){
        AssertUtils.isNull(userForm,"账号或密码不能为空");
        //1、拿着用户名去 查询用户信息
        SysUser sysUser = this.sysUserService.queryByUsername(userForm.getUsername());

        if(sysUser == null){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }
        //2、通过明文加密  对比  密文 是否一致
        String pwd = new SimpleHash("MD5", userForm.getPassword(), sysUser.getSalt(), 1024).toString();
        //3、不一致      返回Result.error()
        if(!pwd.equals(sysUser.getPassword())){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }
        //4、一致     生成token 保存redis中 返回Result.ok()
        Result result = this.userTokenService.createToken(sysUser);
        String url = this.sysUserService.queryUrl(userForm.getUsername());
        Menu menu = JSON.parseObject(url, Menu.class);
        Map map = new HashMap();
        map.put("token",result.getData());
        map.put("menu",menu);
        return new Result().ok(map);
    }
    @ApiOperation("账户设置")
    @GetMapping("/sysUser/preUpdate/{username}")
    @ApiImplicitParam(paramType = "query",name = "username",value ="用户名",dataType ="String",required = true)
    public Result<com.gxa.modules.login.dto.User> preUpdate(@RequestParam("username") String username){
        AssertUtils.isBlank(username, "字符串不为能空");
        com.gxa.modules.login.dto.User user = this.sysUserService.queryByRealname(username);
        return new Result<com.gxa.modules.login.dto.User>().ok(user);
    }
    @ApiOperation("账号信息修改")
    @PutMapping("/sysUser/update")
    public Result update(@RequestBody SysUser sysUser){
        AssertUtils.isNull(sysUser,"账号或密码不能为空");

        ValidatorUtils.validateEntity(sysUser, UpdateGroup.class);
        if (sysUser.getPassword() != null && sysUser.getPassword() != ""){
            SysUser user = this.sysUserService.queryByUsername(sysUser.getUsername());
            String password = new SimpleHash("MD5", sysUser.getPassword(), user.getSalt(), 1024).toString();
            if(!password.equals(user.getPassword())){
                return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"旧密码输入错误");
            }
            String uuid = String.valueOf(UUID.randomUUID());
            sysUser.setSalt(uuid);
            String pwd = new SimpleHash("MD5", sysUser.getConfirmPassword(), uuid, 1024).toString();
            sysUser.setPassword(pwd);
            this.sysUserService.update(sysUser,new UpdateWrapper<SysUser>().eq("user_name",sysUser.getUsername()));
            return new Result<>().ok();
        }
        this.sysUserService.updateUser(sysUser);
        return new Result<>().ok();
    }
}

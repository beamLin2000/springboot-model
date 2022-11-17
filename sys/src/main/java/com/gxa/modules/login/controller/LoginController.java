package com.gxa.modules.login.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.common.utils.AssertUtils;
import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.RedisUtils;
import com.gxa.common.utils.Result;
import com.gxa.common.validator.ValidatorUtils;
import com.gxa.common.validator.group.UpdateGroup;
import com.gxa.modules.login.entity.Menu;
import com.gxa.modules.login.entity.Send;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.form.NoseUserForm;
import com.gxa.modules.login.form.UserForm;
import com.gxa.modules.login.msg.SendCode;
import com.gxa.modules.login.redis.SysUserRedis;
import com.gxa.modules.login.service.SysUserService;
import com.gxa.modules.login.service.UserService;
import com.gxa.modules.login.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Api(tags = "用户接口")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserTokenService userTokenService;

    @ApiOperation("前台用户手机号获取验证码")
    @GetMapping("/login/code/{phoneNum}")
    @ApiImplicitParam(paramType = "query",name = "phoneNum",value ="手机号",dataType ="String",required = true)
    public Result sendCode(@RequestParam("phoneNum") String phoneNum) throws Exception {
        String captcha = SendCode.send(phoneNum);
        userTokenService.createToken(phoneNum,captcha);
        return new Result<>().ok();
    }
    @ApiOperation(value="前台用户手机号登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody NoseUserForm noseUserForm) throws Exception {
        AssertUtils.isNull(noseUserForm,"手机号或验证码不能为空");
        String captcha = userTokenService.validateCaptcha(noseUserForm.getPhone());
        if (StringUtils.isBlank(noseUserForm.getCaptcha())){
            return new Result().error(ErrorCode.CAPTCHA_ERROR,"请输入验证码");
        }
        if (!captcha.equals(noseUserForm.getCaptcha())){
            return new Result().error(ErrorCode.CAPTCHA_ERROR,"验证码错误");
        }
        Result result = null;
        User u = this.userService.queryByPhoneNum(noseUserForm.getPhone());
        User user = new User();
        if(u == null){
            user.setPhoneNumber(noseUserForm.getPhone());
            System.out.println(user.getPhoneNumber());
            System.out.println(user);
            userService.add(user);
            result = this.userTokenService.createToken(user);
        }else {
            result = this.userTokenService.createToken(u);
        }
        Map map = new HashMap();
        map.put("token",result.getData());
        map.put("phoneNumber",noseUserForm.getPhone());
        return new Result().ok(map);
    }
    @ApiOperation(value="后台登录接口")
    @PostMapping("/sys/login")
    public Result sysLogin(@RequestBody UserForm userForm){
        AssertUtils.isNull(userForm,"账号或密码不能为空");
        SysUser sysUser = this.sysUserService.queryByUsername(userForm.getUsername());
        if (sysUser.getStatus() == 0){
            return new Result().error(ErrorCode.ACCOUNT_DISABLE,"账户禁用");
        }
        if(sysUser == null){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }
        String pwd = new SimpleHash("MD5", userForm.getPassword(), sysUser.getSalt(), 1024).toString();
        if(!pwd.equals(sysUser.getPassword())){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }
        Result result = this.userTokenService.createToken(sysUser);
        String url = this.sysUserService.queryUrl(userForm.getUsername());
        Menu menu = JSON.parseObject(url, Menu.class);
        Map map = new HashMap();
        map.put("token",result.getData());
        map.put("menu",menu);
        map.put("user",sysUser);
        return new Result().ok(map);
    }
    @ApiOperation("后台账户设置")
    @GetMapping("/sysUser/preUpdate/{username}")
    @ApiImplicitParam(paramType = "query",name = "username",value ="用户名",dataType ="String",required = true)
    public Result<com.gxa.modules.login.dto.User> preUpdate(@RequestParam("username") String username){
        AssertUtils.isBlank(username, "字符串不为能空");
        com.gxa.modules.login.dto.User user = this.sysUserService.queryByRealname(username);
        return new Result<com.gxa.modules.login.dto.User>().ok(user);
    }
    @ApiOperation("后台账号信息修改")
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

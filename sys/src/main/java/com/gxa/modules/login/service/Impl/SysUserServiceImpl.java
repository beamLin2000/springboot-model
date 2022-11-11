package com.gxa.modules.login.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.login.dto.User;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.mapper.SysUserMapper;
import com.gxa.modules.login.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser queryByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name", username));
    }

    @Override
    public User queryByRealname(String username) {
        SysUser sysUser = baseMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name", username));
        User user =new User();
        user.setUsername(sysUser.getUsername());
        user.setRealName(sysUser.getRealName());
        user.setPhoneNumber(sysUser.getPhoneNumber());
        user.setEmail(sysUser.getEmail());
        return user;
    }
    @Override
    public void updateUser(SysUser sysUser) {
        this.sysUserMapper.updateUser(sysUser);
    }

    @Override
    public String queryUrl(String username) {
        return this.sysUserMapper.queryUrl(username);
    }

}

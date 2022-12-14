package com.gxa.modules.login.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.login.dto.Role;
import com.gxa.modules.login.mapper.RoleMapper;
import com.gxa.modules.login.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryAll(Map<String,Object> params) {
        if (StringUtils.isEmpty(params.get("name"))){
            return this.roleMapper.queryAll();
        }
        return this.roleMapper.queryByname(params.get("name").toString());
    }

    @Override
    public List<String> roleName() {
        return this.roleMapper.roleName();
    }

    @Override
    public void del(String name) {
        this.baseMapper.delete(new QueryWrapper<Role>().eq("name",name));
    }

    @Override
    public void updateStatus(Map<String,Object> params) {
        this.roleMapper.updateStatus(Integer.parseInt(params.get("status").toString()), params.get("name").toString());
    }
}

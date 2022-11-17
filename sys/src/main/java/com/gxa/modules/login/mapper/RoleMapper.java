package com.gxa.modules.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.login.dto.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> queryAll();
    List<String> roleName();
    List<Role> queryByname(String name);
    void updateStatus(@Param("status")Integer status,@Param("name")String name);
}

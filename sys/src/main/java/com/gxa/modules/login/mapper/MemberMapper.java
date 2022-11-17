package com.gxa.modules.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.login.dto.Member;
import com.gxa.modules.login.dto.MemberMannage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    List<Member> queryAll();
    Page<Member> queryA(Page<Member> page);
    Page<Member> queryAllByCondition(Page<Member> page,@Param("username") String username, @Param("role")String role);
    void add(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
    Integer roleId(String role);
    Integer userId(String username);
    void deleteRole(Integer userId);
    void updateStatus(@Param("status")Integer status,@Param("username")String username);
    void updateMember(MemberMannage member);
    MemberMannage queryByName(String username);
    void updateRole(@Param("roleId") Integer roleId,@Param("userId") Integer userId);
}

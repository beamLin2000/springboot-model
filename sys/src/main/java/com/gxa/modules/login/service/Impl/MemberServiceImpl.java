package com.gxa.modules.login.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.login.dto.Member;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.mapper.MemberMapper;
import com.gxa.modules.login.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl  extends ServiceImpl<MemberMapper, Member> implements MemberService{

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public PageUtils queryAll(Map<String, Object> params) {
        if (params.get("role") != "" && params.get("role") != null){
            List<Member> members = this.memberMapper.queryAllByCondition(params.get("username").toString(),params.get("role").toString());
            PageUtils pageUtils = new PageUtils(members, members.size(), Integer.parseInt(params.get("limit").toString()), Integer.parseInt(params.get("page").toString()));
            return pageUtils;
        }
        List<Member> members = this.memberMapper.queryAll();
        PageUtils pageUtils = new PageUtils(members, members.size(), Integer.parseInt(params.get("limit").toString()), Integer.parseInt(params.get("page").toString()));
        return pageUtils;
    }

    @Override
    public void delete(String username) {
        this.memberMapper.delete(new QueryWrapper<Member>().eq("user_name",username));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(Member member) {
        this.memberMapper.insert(member);
        this.memberMapper.add(this.memberMapper.userId(member.getUsername()),this.memberMapper.roleId(member.getUsername()));
    }

    @Override
    public Result queryA(Map<String,Object> params) {
        Page<Member> page = new Page<>(Integer.parseInt(params.get("page").toString()),Integer.parseInt(params.get("limit").toString()));
        Page<Member> memberPage= memberMapper.queryA(page);
        List<Member> members = memberPage.getRecords();
        Integer count = Math.toIntExact(memberPage.getTotal());
        Map map = new HashMap();
        map.put("count",count);
        map.put("currentPage",members);
        return new Result<>().ok(map);
    }

}

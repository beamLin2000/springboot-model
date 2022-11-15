package com.gxa.modules.login.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.AssertUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.login.dto.Member;
import com.gxa.modules.login.mapper.MemberMapper;
import com.gxa.modules.login.service.MemberService;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl  extends ServiceImpl<MemberMapper, Member> implements MemberService{

    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(String username) {
        this.memberMapper.deleteRole(this.memberMapper.userId(username));
        this.memberMapper.delete(new QueryWrapper<Member>().eq("user_name",username));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(Member member) {
        this.memberMapper.insert(member);
        this.memberMapper.add(this.memberMapper.userId(member.getUsername()),this.memberMapper.roleId(member.getRole()));
    }

    @Override
    public Result queryAll(Map<String,Object> params) {
        String username = ObjectUtils.toString(params.get("username"), "");
        String role = ObjectUtils.toString(params.get("role"), "");
        Boolean u = StringUtils.isEmpty(username);
        Boolean r = StringUtils.isEmpty(role);
        System.out.println(u);
        System.out.println(r);
        if (u && r){
            String p = ObjectUtils.toString(params.get("pageNum"), "");
            String l = ObjectUtils.toString(params.get("pageSize"), "");
            Page<Member> page = new Page<>(Integer.parseInt(p),Integer.parseInt(l));
            System.out.println(page);
            Page<Member> memberPage= memberMapper.queryA(page);
            List<Member> members = memberPage.getRecords();
            Integer count = Math.toIntExact(memberPage.getTotal());
            Map map = new HashMap();
            map.put("count",count);
            map.put("currentPage",members);
            map.put("page",Integer.parseInt(params.get("pageNum").toString()));
            map.put("limit",Integer.parseInt(params.get("pageSize").toString()));
            return new Result<>().ok(map);
        }
        if (!u){
            username =  params.get("username").toString();
        }
        if (!r){
            role = params.get("role").toString();
        }
        Page<Member> page = new Page<>(Integer.parseInt(params.get("pageNum").toString()),Integer.parseInt(params.get("pageSize").toString()));
        Page<Member> memberPage = memberMapper.queryAllByCondition(page,username, role);
        List<Member> members = memberPage.getRecords();
        Integer count = Math.toIntExact(memberPage.getTotal());
        Map map = new HashMap();
        map.put("count",count);
        map.put("currentPage",members);
        map.put("page",Integer.parseInt(params.get("pageNum").toString()));
        map.put("limit",Integer.parseInt(params.get("pageSize").toString()));
        return new Result<>().ok(map);
    }

    @Override
    public void updateStatus(Map<String, Object> params) {
        this.memberMapper.updateStatus(Integer.parseInt(params.get("status").toString()), params.get("username").toString());
    }

}

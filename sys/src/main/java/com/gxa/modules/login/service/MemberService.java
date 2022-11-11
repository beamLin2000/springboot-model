package com.gxa.modules.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.login.dto.Member;

import java.util.List;
import java.util.Map;

public interface MemberService extends IService<Member> {
    PageUtils queryAll(Map<String,Object> params);

    void delete(String username);
    void add(Member member);
    Result queryA(Map<String,Object> params);
}

package com.gxa.modules.fristpage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxa.modules.fristpage.entity.Msg;
import com.gxa.modules.fristpage.mapper.MsgMapper;
import com.gxa.modules.fristpage.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgMapper msgMapper;
    @Override
    public List<Msg> queryAllMsg(Integer id) {

        List<Msg> msgs = this.msgMapper.selectList(new QueryWrapper<Msg>().eq("u_id",id));
        return msgs;
    }

    @Override
    public boolean saveMsg(Msg msg,String id) {
        msg.setId(id);
        this.msgMapper.insert(msg);
        return true;
    }
}

package com.gxa.modules.fristpage.service;

import com.gxa.modules.fristpage.entity.Msg;

import java.util.List;

public interface MsgService {
    List<Msg> queryAllMsg(Integer id);
    boolean saveMsg(Msg msg);
}

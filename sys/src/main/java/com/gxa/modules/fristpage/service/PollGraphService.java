package com.gxa.modules.fristpage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.fristpage.entity.Goods;
import com.gxa.modules.fristpage.entity.PollGraph;

import java.util.List;
import java.util.Map;

public interface PollGraphService extends IService<PollGraph> {
    List queryPollGraph();
}

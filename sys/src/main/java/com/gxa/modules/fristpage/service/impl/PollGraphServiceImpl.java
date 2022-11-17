package com.gxa.modules.fristpage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.fristpage.entity.PollGraph;
import com.gxa.modules.fristpage.mapper.PollGraphMapper;
import com.gxa.modules.fristpage.service.PollGraphService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PollGraphServiceImpl  extends ServiceImpl<PollGraphMapper, PollGraph> implements PollGraphService {

    @Override
    public List queryPollGraph() {

        List<PollGraph> pollGraphs = this.baseMapper.selectList(new QueryWrapper<PollGraph>().eq("`status`","1"));
        return pollGraphs;
    }
}

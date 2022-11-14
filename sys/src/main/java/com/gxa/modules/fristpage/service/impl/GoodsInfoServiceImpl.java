package com.gxa.modules.fristpage.service.impl;

import com.gxa.modules.fristpage.entity.GoodsInfo;
import com.gxa.modules.fristpage.mapper.GoodsInfoMapper;
import com.gxa.modules.fristpage.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public GoodsInfo queryByName(String name, Date fristDay, Date lastDay) {
        GoodsInfo goodsInfo = this.goodsInfoMapper.queryByName(name);
        Integer num = this.goodsInfoMapper.queryNum(name, fristDay, lastDay);
        goodsInfo.setNum(num);
        return goodsInfo;
    }
}

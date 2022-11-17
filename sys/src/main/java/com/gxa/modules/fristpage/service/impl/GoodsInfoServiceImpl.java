package com.gxa.modules.fristpage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.fristpage.entity.GoodsInfo;
import com.gxa.modules.fristpage.entity.GoodsInfodto;
import com.gxa.modules.fristpage.mapper.GoodsInfoMapper;
import com.gxa.modules.fristpage.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class GoodsInfoServiceImpl extends ServiceImpl<GoodsInfoMapper,GoodsInfo> implements GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public GoodsInfodto queryByName(String name, Double price) {
        QueryWrapper<GoodsInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("drug_name",name);
        wrapper.eq("price",price);
        wrapper.last("limit 1");
        GoodsInfo goodsInfo = this.baseMapper.selectOne(wrapper);
        GoodsInfodto goodsInfodto = new GoodsInfodto(goodsInfo);
        return goodsInfodto;
    }
}

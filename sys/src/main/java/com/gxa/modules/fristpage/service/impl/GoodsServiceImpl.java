package com.gxa.modules.fristpage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.fristpage.entity.Goods;
import com.gxa.modules.fristpage.mapper.GoodsMapper;
import com.gxa.modules.fristpage.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageUtils queryByStr(Map<String,Object>param) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("drug_name", param.get("str"));
        IPage<Goods> page = this.page(new Query<Goods>().getPage(param), wrapper);

        PageUtils p = new PageUtils(page);
        return p;
    }

    @Override
    public PageUtils queryByin(Map<String, Object> param) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("shelves","1");
        wrapper.like("main_function", param.get("str"));
        IPage<Goods> page = this.page(new Query<Goods>().getPage(param), wrapper);

        PageUtils p = new PageUtils(page);
        return p;
    }


    @Override
    public PageUtils queryRecommed(Map<String, Object> param) {
        IPage<Goods> page = this.page(new Query<Goods>().getPage(param), new QueryWrapper<Goods>().eq("recommend", "是"));
        return new PageUtils(page);
    }


}

package com.gxa.modules.fristpage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public Map queryByStr(String str) {
        List<Goods> goods = this.baseMapper.selectList(new QueryWrapper<Goods>().like("drug_name",str));
        Map map = new HashMap();
        map.put("goods",goods);
        return map;
    }
}

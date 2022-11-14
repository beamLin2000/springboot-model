package com.gxa.modules.goods.goodsService.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.goods.goodsEntity.Symptom;
import com.gxa.modules.goods.goodsMapper.SymptomMapper;
import com.gxa.modules.goods.goodsService.SymptomService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SymptomServiceImpl extends ServiceImpl<SymptomMapper,Symptom> implements SymptomService {

    @Override
    public PageUtils list(Map<String, Object> params) {
        IPage<Symptom> page = this.page(new Query<Symptom>().getPage(params),
                new QueryWrapper<Symptom>().eq("`rank`","一级"));
        return new PageUtils(page);
    }

    @Override
    public PageUtils listTwo(Map<String, Object> params) {
        IPage<Symptom> page = this.page(new Query<Symptom>().getPage(params),
                new QueryWrapper<Symptom>().eq("`rank`","二级"));

        return new PageUtils(page);
    }
}

package com.gxa.modules.goods.goodsService.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.common.utils.RedisUtils;
import com.gxa.modules.goods.goodsEntity.Symptom;
import com.gxa.modules.goods.goodsMapper.SymptomMapper;
import com.gxa.modules.goods.goodsService.SymptomService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    public void symptomUpdate(Symptom symptom){

        Date date = new Date();
        symptom.setAddTime(date);

        Map map = new HashMap();
        map.put("version",symptom.getVersion());
        map.put("id",symptom.getId());

        this.baseMapper.update(symptom,new UpdateWrapper<Symptom>().allEq(map));

        symptom.setVersion(symptom.getVersion()+1);
        this.baseMapper.update(symptom,new UpdateWrapper<Symptom>().eq("id",symptom.getId()));
    }

    public void symptomInsert(Symptom symptom){
        Date date = new Date();
        symptom.setAddTime(date);

        symptom.setRank("一级");
        this.baseMapper.insert(symptom);
    }

    public void symptomInsertRank(Symptom symptom){

        Date date = new Date();
        symptom.setAddTime(date);
        symptom.setRank("二级");

//        medicinal.setHigherLevel(medicinal.getId());
        this.baseMapper.insert(symptom);
    }

    public void symptomTwoInsert(Symptom symptom){

        Date date = new Date();
        symptom.setAddTime(date);

        symptom.setRank("一级");
        this.baseMapper.insert(symptom);
    }

    public void symptomTwoUpdate(Symptom symptom){
        Date date = new Date();
        symptom.setAddTime(date);

        Map map = new HashMap();
        map.put("version",symptom.getVersion());
        map.put("id",symptom.getId());

        this.baseMapper.update(symptom,new UpdateWrapper<Symptom>().allEq(map));

        symptom.setVersion(symptom.getVersion()+1);
        this.baseMapper.update(symptom,new UpdateWrapper<Symptom>().eq("id",symptom.getId()));
    }
}

package com.gxa.modules.goods.goodsService.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.common.utils.RedisUtils;
import com.gxa.modules.goods.goodsEntity.Medicinal;
import com.gxa.modules.goods.goodsMapper.MedicinalMapper;
import com.gxa.modules.goods.goodsService.MedicinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MedicinalServiceImpl extends ServiceImpl<MedicinalMapper, Medicinal> implements MedicinalService {

//    @Autowired
//    private MedicinalService medicinalService;

    /**
     * 查询药品一级分类的所有数据
     * @param params 分页参数
     * @return
     */
    @Override
    public PageUtils list(Map<String, Object> params) {
        IPage<Medicinal> page = this.page(new Query<Medicinal>().getPage(params),
                new QueryWrapper<Medicinal>().eq("`rank`","一级"));

        return new PageUtils(page);
    }

    @Override
    public PageUtils listTwo(Map<String, Object> params) {
        IPage<Medicinal> page = this.page(new Query<Medicinal>().getPage(params),
                new QueryWrapper<Medicinal>().eq("`rank`","二级"));

        return new PageUtils(page);
    }

    public void medicinalUpdate(Medicinal medicinal){

        Date date = new Date();
        medicinal.setAddTime(date);

        Map map = new HashMap();
        map.put("version",medicinal.getVersion());
        map.put("id",medicinal.getId());

        this.baseMapper.update(medicinal,new UpdateWrapper<Medicinal>().allEq(map));

        medicinal.setVersion(medicinal.getVersion()+1);
        this.baseMapper.update(medicinal,new UpdateWrapper<Medicinal>().eq("id",medicinal.getId()));
    }

    public void medicinalInsert(Medicinal medicinal){
        Date date = new Date();
        medicinal.setAddTime(date);

        medicinal.setId(UUID.randomUUID().toString());
        medicinal.setRank("一级");
        this.baseMapper.insert(medicinal);
    }

    public void medicinalInsertRank(Medicinal medicinal){
        Date date = new Date();
        medicinal.setAddTime(date);
        medicinal.setRank("二级");

        medicinal.setId(UUID.randomUUID().toString());

//        medicinal.setHigherLevel(medicinal.getId());
        this.baseMapper.insert(medicinal);
    }

    public void medicinalTwoInsert(Medicinal medicinal){
        Date date = new Date();
        medicinal.setAddTime(date);
        medicinal.setRank("二级");

        medicinal.setId(UUID.randomUUID().toString());

//        medicinal.setHigherLevel(medicinal.getId());
        this.baseMapper.insert(medicinal);

    }

    public void medicinalTwoUpdate(Medicinal medicinal){

        Date date = new Date();
        medicinal.setAddTime(date);

        Map map = new HashMap();
        map.put("version",medicinal.getVersion());
        map.put("id",medicinal.getId());

        this.baseMapper.update(medicinal,new UpdateWrapper<Medicinal>().allEq(map));

        medicinal.setVersion(medicinal.getVersion()+1);
        this.baseMapper.update(medicinal,new UpdateWrapper<Medicinal>().eq("id",medicinal.getId()));

    }
}
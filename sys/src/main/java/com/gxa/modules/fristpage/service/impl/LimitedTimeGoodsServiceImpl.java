package com.gxa.modules.fristpage.service.impl;

import com.gxa.modules.fristpage.entity.LimitedTimeGoods;
import com.gxa.modules.fristpage.mapper.LimitedTimeGoodsMapper;
import com.gxa.modules.fristpage.service.LimitedTimeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LimitedTimeGoodsServiceImpl implements LimitedTimeGoodsService {
   @Autowired
    private LimitedTimeGoodsMapper limitedTimeGoodsMapper;


    @Override
    public List<LimitedTimeGoods> queryoen() {
        List<LimitedTimeGoods> queryone = this.limitedTimeGoodsMapper.queryone();
        for (LimitedTimeGoods goods :
                queryone) {
            Integer integer = this.limitedTimeGoodsMapper.queryStockByName(goods.getDrugName(), goods.getActivityTime(), goods.getEndTime());
            int parseInt = Integer.parseInt(goods.getStock());
            if (integer!=null&&integer!=0){
                String str = (float)integer/parseInt*100 +"%";
                goods.setStock(str);
            }else {
                goods.setStock("0%");
            }
        }

        return queryone;
    }
}

package com.gxa.modules.homepage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.homepage.entity.GoodsAndSales;
import com.gxa.modules.homepage.mapper.GoodsAndSalesMapper;
import com.gxa.modules.homepage.service.GoodsAndSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GoodsAndSalesServiceImpl extends ServiceImpl<GoodsAndSalesMapper,GoodsAndSales> implements GoodsAndSalesService {
    @Autowired
    private GoodsAndSalesMapper goodsAndSalesMapper;
    @Override
    public GoodsAndSales queryGoodsAndSalesByToday(Date today,Date tomorrow) {
        GoodsAndSales goodsAndSales = this.goodsAndSalesMapper.queryGoodsAndSalesByToday(today,tomorrow);
        GoodsAndSales goodsAndSales1 = this.goodsAndSalesMapper.queryGoodsAndSales();
        GoodsAndSales goodsAndSales2 = new GoodsAndSales(goodsAndSales1.getGoodNum(),goodsAndSales1.getTotalSaleMoney()
        ,goodsAndSales.getTodayOrder(),goodsAndSales.getTodaySaleMoney());
        return goodsAndSales2;
    }
}

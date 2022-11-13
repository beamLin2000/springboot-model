package com.gxa.modules.homepage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.homepage.entity.GoodsAndSales;

import java.util.Date;

public interface GoodsAndSalesService extends IService<GoodsAndSales> {
    GoodsAndSales queryGoodsAndSalesByToday(Date today,Date tomorrow);
}

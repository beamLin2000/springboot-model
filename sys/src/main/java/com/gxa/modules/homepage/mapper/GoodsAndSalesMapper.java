package com.gxa.modules.homepage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.homepage.entity.GoodsAndSales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface GoodsAndSalesMapper extends BaseMapper<GoodsAndSales> {
    GoodsAndSales queryGoodsAndSalesByToday(@Param("today") Date today, @Param("tomorrow") Date tomorrow);
    GoodsAndSales queryGoodsAndSales();
}

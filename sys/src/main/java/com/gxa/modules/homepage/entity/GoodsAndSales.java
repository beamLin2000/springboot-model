package com.gxa.modules.homepage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.hpsf.Decimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsAndSales {
    @ApiModelProperty("商品总数")
    Integer goodNum;
    @ApiModelProperty("销售总额")
    Double totalSaleMoney;
    @ApiModelProperty("今日订单")
    Integer todayOrder;
    @ApiModelProperty("今日销售总额")
    Double todaySaleMoney;
}

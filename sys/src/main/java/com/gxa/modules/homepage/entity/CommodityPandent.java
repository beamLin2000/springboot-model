package com.gxa.modules.homepage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityPandent {
    @ApiModelProperty("今日新增")
    private Integer todayNewAdded;
    @ApiModelProperty("昨日新增")
    private Integer yesterdayNewAdded;
    @ApiModelProperty("本月新增")
    private Integer monthNewAdded;
    @ApiModelProperty("商品总数")
    private Integer totalGoodsNum;
}

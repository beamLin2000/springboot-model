package com.gxa.modules.confirmOrder.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//确认的商品有哪些
public class ConfirmOrder {
    @ApiModelProperty(name = "id",value = "id",dataType = "Integer",required = false)
    private Integer id;
    @ApiModelProperty(name = "id",value = "购物车的商品的id",dataType = "Integer",required = true)
    private Integer shoppingId;
    @ApiModelProperty(name = "id",value = "订单编号",dataType = "String",required = false)
    private String orderNo;
}

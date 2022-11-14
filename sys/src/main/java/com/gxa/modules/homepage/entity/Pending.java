package com.gxa.modules.homepage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pending {
    @ApiModelProperty("待发货订单")
    private Integer waitOrder;
    @ApiModelProperty("待处理退款")
    private Integer waitRefund;
    @ApiModelProperty("待审核商品")
    private Integer waitToExamine;
    @ApiModelProperty("待确认收货")
    private Integer waitConfirmReceipt;
    @ApiModelProperty("库存预警")
    private Integer inventoryAlert;
}

package com.gxa.modules.sys.form.backstage.promotion.timeLimitedSecondKill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :林溪
 * @date : 2022/11/11 10:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("限时秒杀->新增/编辑秒杀")
public class AddTimeLimitedSecondKill {
    @ApiModelProperty(name = "drugName",value = "药品名称",dataType = "String",required = true)
    private String drugName;
    @ApiModelProperty(name = "price",value = "价格",dataType = "String",required = true)
    private String price;
    @ApiModelProperty(name = "secondKillPrice",value = "秒杀价格",dataType = "Double",required = true)
    private Double secondKillPrice;
    @ApiModelProperty(name = "activityTime",value = "活动时间",dataType = "String",required = true)
    private String activityTime;
    @ApiModelProperty(name = "secKillInventory",value = "秒杀库存",dataType = "Integer",required = true)
    private Integer secKillInventory;
    @ApiModelProperty(name = "limitedPurchaseQuantity",value = "限购数量",dataType = "Integer",required = true)
    private Integer limitedPurchaseQuantity;
}

package com.gxa.modules.shoppingCart.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//购物车
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_shopping_cart")
public class ShoppingCart {
    @ApiModelProperty(name = "id",value = "id",dataType = "Integer",required = true)
    private Integer id;

    @ApiModelProperty(name = "dId",value = "药品id",dataType = "Integer")
    @TableField("d_id")
    private Integer dId;

    @ApiModelProperty(name = "orderNO",value = "订单号",dataType = "String")
    private String orderNO;

    @ApiModelProperty(name = "quantity",value = "药品数量",dataType = "Integer")
    private Integer quantity;

    @ApiModelProperty(name = "uId",value = "当前用户id",dataType = "Integer")
    @TableField("u_id")
    private Integer uId;

//    @TableField("shopp_state")
//    private Integer shoppState;

    @ApiModelProperty(name = "shopp_state",value = "商品状态",dataType = "Integer")
    @TableField("shopp_state")
    private Integer shoppState;

    @ApiModelProperty(name = "prescriptionId",value = "处方id",dataType = "Integer")
    private Integer prescriptionId;

}

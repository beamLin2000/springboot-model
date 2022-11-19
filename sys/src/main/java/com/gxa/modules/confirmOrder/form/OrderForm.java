package com.gxa.modules.confirmOrder.form;

import com.gxa.modules.confirmOrder.entity.Address;
import com.gxa.modules.confirmOrder.entity.ConfirmOrder;
import com.gxa.modules.confirmOrder.entity.DrugUserInformation;
import com.gxa.modules.confirmOrder.entity.Order;
import com.gxa.modules.shoppingCart.entity.ShoppingCart;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponUsageInformation;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {
    @ApiModelProperty(name = "address",value = "地址信息")
    private Address address;

    @ApiModelProperty(name = "shoppingCart",value = "购物车购买商品信息")
    private List<ShoppingCart> shoppingCart;

    @ApiModelProperty(name = "drugUserInformation",value = "用药人信息")
    private DrugUserInformation drugUserInformation;

    @ApiModelProperty(name = "order",value = "订单信息")
    private Order order;

    @ApiModelProperty(name = "prescriptionId",value = "把处方id放进来")
    private Integer prescriptionId;

    @ApiModelProperty(name = "couponUsageInformation",value = "优惠券信息")
    private CouponUsageInformation couponUsageInformation;
}

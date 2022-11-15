package com.gxa.modules.confirmOrder.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gxa.modules.shoppingCart.entity.Drug;
import com.gxa.modules.shoppingCart.entity.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//用户购买的商品
public class ConfirmShoppCart {
    private Integer id;
    private Integer shoppingId;
    private Integer orderNo;
    private Drug drug;
    private ShoppingCart shoppingCart;

}

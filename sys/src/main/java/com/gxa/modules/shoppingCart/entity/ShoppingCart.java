package com.gxa.modules.shoppingCart.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//购物车
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_shopping_cart")
public class ShoppingCart {
    private Integer id;
    @TableField("d_id")
    private Integer dId;
    private Integer quantity;
    @TableField("u_id")
    private Integer uId;
    @TableField("shopp_state")
    private Integer shoppState;

}

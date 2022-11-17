package com.gxa.modules.shoppingCart.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gxa.modules.shoppingCart.entity.Drug;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//发送的购物车数据
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    private Integer id;
    @TableField("d_id")
    private Integer dId;
    @ApiModelProperty(name = "id",value = "药品名称",dataType = "Integer")
    private Integer quantity;
    @TableField("u_id")
    private Integer uId;
    @TableField("shopp_state")
    private Integer shoppState;
    private Drug drug;

}

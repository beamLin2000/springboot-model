package com.gxa.modules.fristpage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_shopping_cart")
public class Cart {
    @ApiModelProperty(value = "id",hidden = true)
    private Integer id;
    @ApiModelProperty(value = "drugId")
    @TableField("d_id")
    private Integer drugId;
    @ApiModelProperty(value = "userId",hidden = true)
    @TableField("u_id")
    private Integer userId;
    @ApiModelProperty(value = "num")
    @TableField("quantity")
    private Integer num;
}

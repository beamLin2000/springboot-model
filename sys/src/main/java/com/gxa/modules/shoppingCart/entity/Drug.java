package com.gxa.modules.shoppingCart.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 药品管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("drug")
public class Drug {

    private int id;
    @TableField("drug_name")
    private String drugName;//药品名称
    @TableField("picture_url")
    private String pictureUrl;//列表图
    private Double price;//价格
    private String prescribe;//是否处方
    private String specifications;//规格


}

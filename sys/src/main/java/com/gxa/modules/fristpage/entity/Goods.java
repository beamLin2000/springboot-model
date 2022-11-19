package com.gxa.modules.fristpage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("drug")
public class Goods {
    @TableField("drug_name")
    private String drugName;
    @TableField("specifications")
    private String specifications;
    @TableField("picture_url")
    private String pictureUrl;
    @TableField("price")
    private Double price;
    @TableField("id")
    private Integer id;
}

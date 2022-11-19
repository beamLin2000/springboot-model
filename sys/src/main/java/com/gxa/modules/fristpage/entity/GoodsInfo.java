package com.gxa.modules.fristpage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("drug")
public class GoodsInfo {
    @TableField("id")
    private Integer id;
    @TableField("detailed_introduction")
    private String imageUrl;
    @TableField("price")
    private Double price;
    @TableField("sales_volume")
    private Integer num;
    @TableField("drug_name")
    private String name;
    @TableField("prescribe")
    private String rx;
    @TableField("specifications")
    private String spec;//规格
    @TableField("main_function")
    private String mainFunction;
    @TableField("`usage`")
    private String usage;
    @TableField("document_number")
    private String approvalNum;
    @TableField("rotational_chart_url")
    private String instructionsUrl;
    @TableField("instructions_url")
    private String detailsUrl;
}

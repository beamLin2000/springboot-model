package com.gxa.modules.goods.goodsEntity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("药品管理表单")
public class Drug {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("药品名称")
    @TableField("`drug_name`")
    private String drugName;//药品名称

    @ApiModelProperty("列表图")
    @TableField("`picture_url`")
    private String pictureUrl;//列表图

    @ApiModelProperty("价格")
    @TableField("`price`")
    private Double price;//价格

    @ApiModelProperty("是否上架，0表示未上架，1表示已上架")
    @TableField("`shelves`")
    private String shelves;//是否   上架    用"0"表示未上架，"1"表示已上架

    @ApiModelProperty("是否处方     是处方，用\"是\"表示，不是用\"否\"")
    @TableField("`prescribe`")
    private String prescribe;//是否处方     是处方，用"是"表示，不是用"否"

    @ApiModelProperty("审核状态，三种状态，待审核，未通过，已通过")
    @TableField("`state`")
    private String state;//审核状态

    @ApiModelProperty("库存")
    @TableField("`stock`")
    private int stock;//库存

    @ApiModelProperty("发布时间")
    @TableField("`release_time`")
    private Date releaseTime;//发布时间


    @ApiModelProperty("药品编号")
    @TableField("`drug_code`")
    private String drugCode;//药品编号

    @ApiModelProperty("轮播图")
    @TableField("`rotational_chart_url`")
    private String rotationalChartUrl;//轮播图   地址

    @ApiModelProperty("是否推荐 \"是\"表示推荐，\"否\"表示不推荐")
    @TableField("`recommend`")
    private String recommend;//是否推荐 "是"表示推荐，"否"表示不推荐

//    @ApiModelProperty("药品二级分类外键id")
    @TableField("`medicinal_id`")
    private String medicinalId;//药品二级分类外键id

//    @ApiModelProperty("id")
    @TableField("`symptom_id`")
    private String symptomId;//症状二级分类外键id


    @ApiModelProperty("规格")
    @TableField("`specifications`")
    private String specifications;//规格


    @ApiModelProperty("库存预警")
    @TableField("`stock_warning`")
    private int stockWarning;//库存预警

    @ApiModelProperty("是否免运费，是或否")
    @TableField("`free_shipping`")
    private String freeShipping;//是否免运费,是或否

    @ApiModelProperty("批准文号")
    @TableField("`document_number`")
    private String documentNumber;//批准文号

    @ApiModelProperty("主治功能")
    @TableField("`main_function`")
    private String mainFunction;//主治功能

    @ApiModelProperty("用法用量")
    @TableField("`usage`")
    private String usage;//用法用量

    @ApiModelProperty("说明书图片路径")
    @TableField("`instructions_url`")
    private String instructionsUrl;//说明书图片路径

    @ApiModelProperty("药企")
    @TableField("`business`")
    private String business;//药企

    @ApiModelProperty("详细介绍")
    @TableField("`detailed_introduction`")
    private String detailedIntroduction;//详细介绍


    @ApiModelProperty("版本号")
    @TableField("`version`")
    private int version;//版本号


    @ApiModelProperty("备注")
    @TableField("`remarks`")
    private String remarks;//备注


    @ApiModelProperty("症状")
    @TableField(exist = false)
    private String symptom;//症状

    @ApiModelProperty("药品分类")
    @TableField(exist = false)
    private String medicinal;//药品分类



}

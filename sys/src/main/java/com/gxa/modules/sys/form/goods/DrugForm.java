package com.gxa.modules.sys.form.goods;

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
public class DrugForm {

    private int id;
    private String drugName;//药品名称
    private String pictureUrl;//列表图
    private Double price;//价格
    private String shelves;//是否   上架
    private String prescribe;//是否处方
    private String state;//审核状态
    private String stock;//库存
    private Date releaseTime;//发布时间


    private String drugCode;//药品编号
    private String rotationalChartUrl;//轮播图   地址
    private String recommend;//是否推荐

    private int medicinalId;//药品分类外键id
    private int symptomId;//症状分类外键id

    private String specifications;//规格

    private int stockWarning;//库存预警
    private String freeShipping;//是否免运费
    private String documentNumber;//批准文号
    private String mainFunction;//主治功能
    private String usage;//用法用量
    private String instructionsUrl;//说明书图片路径
    private String business;//药企
    private String detailedIntroduction;//详细介绍

    private int version;//版本号



}

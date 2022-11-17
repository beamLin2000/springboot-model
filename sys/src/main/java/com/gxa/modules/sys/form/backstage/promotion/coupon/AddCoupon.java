package com.gxa.modules.sys.form.backstage.promotion.coupon;

import com.gxa.modules.goods.goodsEntity.Drug;
import com.gxa.modules.goods.goodsEntity.Medicinal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :林溪
 * @date : 2022/11/11 9:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("优惠券管理->新增优惠券")
public class AddCoupon {
    @ApiModelProperty(name = "couponType",value = "优惠券类型",dataType = "String")
    private String couponType;
    @ApiModelProperty(name = "couponName",value = "输入标题",dataType = "String")
    private String couponName;
    @ApiModelProperty(name = "couponDescribe",value = "优惠券描述",dataType = "String")
    private String couponDescribe;
    @ApiModelProperty(name = "couponMoney",value = "优惠券金额",dataType = "Double")
    private Double  couponMoney;
    @ApiModelProperty(name = "expirationDate",value = "有效期",dataType = "String")
    private String expirationDate;
    @ApiModelProperty(name = "useThreshold",value = "使用门槛",dataType = "Double")
    private Double useThreshold;
    @ApiModelProperty(name = "totalCirculation",value = "总发行量",dataType = "Integer")
    private Integer totalCirculation;
    @ApiModelProperty(name = "limitPerPerson",value = "每人限领",dataType = "Integer")
    private Integer limitPerPerson;
    @ApiModelProperty(name = "useMembers",value = "使用会员",dataType = "Integer")
    private Integer useMembers;
    @ApiModelProperty(name = "usableGoods",value = "可使用商品",dataType = "Integer")
    private Integer usableGoods;


    @ApiModelProperty(name = "specifyProduct",value = "指定商品",dataType = "Array")
    private List<Drug> specifyProduct;


    @ApiModelProperty(name = "specifyClassification",value = "指定分类",dataType = "Array")
    private List<Medicinal> specifyClassification;



}

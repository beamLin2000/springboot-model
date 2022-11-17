package com.gxa.modules.sys.entity.backStage.promotion.couponManagement;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author LXD
 * @Date 2022/11/16 9:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("优惠券管理添加")
@TableName("promotion_coupon_management")
public class CouponManagerAddAndEdit {
    @TableId(value = "id")
    @ApiModelProperty(name = "id",value = "id",dataType = "String")
    private String id;

    @TableField(value = "coupon_name")
    @ApiModelProperty(name = "couponName",value = "优惠券名称",dataType = "String")
    private String couponName;

    @TableField(value = "coupon_money")
    @ApiModelProperty(name = "couponMoney",value = "面值",dataType = "Double")
    private Double money;

    @TableField(value = "coupon_type")
    @ApiModelProperty(name = "couponType",value = "优惠券类型",dataType = "String")
    private String couponType;



    @TableField(value = "expiration_date")
    @ApiModelProperty(name = "expirationDate",value = "有效期",dataType = "String")
    private String expirationDate;

    /*
    新增的一些实体类
     */

    @TableField(value = "coupon_describe")
    @ApiModelProperty(name = "couponDescribe",value = "优惠券描述",dataType = "String")
    private String couponDescribe;

    @TableField(value = "use_threshold")
    @ApiModelProperty(name = "useThreshold",value = "使用门槛",dataType = "String")
    private String useThreshold;

    @TableField(value = "total_circulation")
    @ApiModelProperty(name = "totalCirculation",value = "总发行量",dataType = "Integer")
    private Integer totalCirculation;

    @TableField(value = "get_threshold")
    @ApiModelProperty(name = "getThreshold",value = "获取门槛",dataType = "String")
    private String getThreshold;

    @TableField(value = "limited_collar")
    @ApiModelProperty(name = "limitedCollar",value = "每人限领",dataType = "String")
    private String limitedCollar;

    @TableField(value = "use_members")
    @ApiModelProperty(name = "useMembers",value = "使用会员",dataType = "String")
    private String useMembers;

    @TableField(value = "usable_goods")
    @ApiModelProperty(name = "usableGoods",value = "可使用商品",dataType = "String")
    private String usableGoods;


    @ApiModelProperty(name = "specifyProduct",value = "指定商品",dataType = "Array")
    private List<CouponAddDrug> specifyProduct;

    @ApiModelProperty(name = "specifyClassification",value = "指定分类",dataType = "Array")
    private List<CouponAddClass> specifyClassification;


    @TableField(value = "version_id")
    @ApiModelProperty(name = "versionId",value = "版本号，保证幂等",dataType = "Integer")
    private Integer versionId;
}

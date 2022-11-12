package com.gxa.modules.sys.entity.backStage.promotion.couponManagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gxa.modules.sys.entity.goods.Drug;
import com.gxa.modules.sys.entity.goods.Medicinal;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author LXD
 * @Date 2022/11/11 9:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("promotion_coupon_management")
public class CouponManagementAll {
    @TableId(value = "id",type = IdType.AUTO)
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

    @TableField(value = "status")
    @ApiModelProperty(name = "status",value = "状态",dataType = "Integer")
    private Integer status;

    @TableField(value = "amount_issued")
    @ApiModelProperty(name = "amountIssued", value = "发放量",dataType = "Integer")
    private Integer amountIssued;

    @TableField(value = "use_issued")
    @ApiModelProperty(name = "use_issued",value = "使用量",dataType = "Integer")
    private Integer useIssued;

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

    @TableField(value = "limited_collar")
    @ApiModelProperty(name = "limitedCollar",value = "每人限领",dataType = "String")
    private String limitedCollar;

    @TableField(value = "use_members")
    @ApiModelProperty(name = "useMembers",value = "使用会员",dataType = "Integer")
    private Integer useMembers;

    @TableField(value = "usable_goods")
    @ApiModelProperty(name = "usableGoods",value = "可使用商品",dataType = "Integer")
    private Integer usableGoods;


    @ApiModelProperty(name = "specifyProduct",value = "指定商品",dataType = "Array")
    private List<CouponAddDrug> specifyProduct;

    @ApiModelProperty(name = "specifyClassification",value = "指定分类",dataType = "Array")
    private List<CouponAddClass> specifyClassification;

    /*
    查看详细的优惠券信息添加实体类
     */

    @TableField(value = "received")
    @ApiModelProperty(name = "received",value = "已领取",dataType = "Integer")
    private Integer received;

    @TableField(value = "unclaimed")
    @ApiModelProperty(name = "unclaimed",value = "未领取",dataType = "Integer")
    private Integer unclaimed;

    @TableField(value = "not_used")
    @ApiModelProperty(name = "notUsed",value = "未使用",dataType = "Integer")
    private Integer notUsed;

    /**
     * 优惠券列表券
     */
    private List<CouponUsageInformation> couponUsageInformations;

}

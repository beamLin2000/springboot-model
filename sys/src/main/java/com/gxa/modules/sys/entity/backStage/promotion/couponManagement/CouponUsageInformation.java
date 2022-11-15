package com.gxa.modules.sys.entity.backStage.promotion.couponManagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LXD
 * @Date 2022/11/11 19:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("优惠券使用信息")
@TableName("promotion_couponUsageInformation")
public class CouponUsageInformation {
    @TableId(value = "id")
    @ApiModelProperty(name = "id",value = "id",dataType = "Integer")
    private String id;

    @TableField(value = "member")
    @ApiModelProperty(name = "member",value = "会员",dataType = "String")
    private String member;

    @TableField(value = "coupon_type")
    @ApiModelProperty(name = "couponType",value = "领取方式",dataType = "String")
    private String couponType;

    @TableField(value = "collection_time")
    @ApiModelProperty(name = "collectionTime",value = "领取时间",dataType = "String")
    private String collectionTime;

    @TableField(value = "current_state")
    @ApiModelProperty(name = "currentState",value = "当前状态",dataType = "String")
    private String currentState;

    @TableField(value = "usage_time")
    @ApiModelProperty(name = "usageTime",value = "使用时间",dataType = "String")
    private String usageTime;

    @TableField(value = "order_number")
    @ApiModelProperty(name = "orderNumber",value = "订单号",dataType = "String")
    private String orderNumber;

    @TableField(value = "coupon_management_id")
    @ApiModelProperty(name = "couponManagementId",value = "优惠券的id",dataType = "String")
    private String couponManagementId;

    @TableField(value = "t_user_id")
    @ApiModelProperty(name = "userId",value = "用户id",dataType = "String")
    private String userId;

    @TableField(value = "version_id")
    @ApiModelProperty(name = "versionId",value = "版本号，保证幂等",dataType = "Integer")
    private Integer versionId;


}

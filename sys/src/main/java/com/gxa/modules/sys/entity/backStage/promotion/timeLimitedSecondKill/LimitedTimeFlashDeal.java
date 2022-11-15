package com.gxa.modules.sys.entity.backStage.promotion.timeLimitedSecondKill;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LXD
 * @Date 2022/11/11 10:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("promotion_limited_time_flash_deal")
public class LimitedTimeFlashDeal {
    @TableId("id")
    @ApiModelProperty(name = "id",value = "id",dataType = "String")
    private String id;
    @TableField("drug_code")
    @ApiModelProperty(name = "drugCode",value = "药品编号",dataType = "String")
    private String drugCode;

    @TableField("drug_name")
    @ApiModelProperty(name = "drugName",value = "药品名称",dataType = "String")
    private String drugName;

    @TableField("price")
    @ApiModelProperty(name = "price",value = "秒杀价格",dataType = "Double")
    private Double price;

    @TableField("second_kill_price")
    @ApiModelProperty(name = "secondKillPrice",value = "秒杀价格",dataType = "Double")
    private Double secondKillPrice;

    @TableField("activity_time_start")
    //@ApiModelProperty(name = "activityTimeStart",value = "活动时间开始",dataType = "String")
    private String activityTimeStart;

    @TableField("activity_time_end")
    //@ApiModelProperty(name = "activityTime",value = "活动时间结束",dataType = "String")
    private String activityTimeEnd;

    @TableField(exist = false)
    @ApiModelProperty(name = "activityTime",value = "活动时间",dataType = "String")
    private String activityTime;

    @TableField("sec_kill_numbers")
    @ApiModelProperty(name = "secKillNumbers",value = "已秒数量",dataType = "Integer")
    private String secKillNumbers;

    @TableField("status")
    @ApiModelProperty(name = "status",value = "状态",dataType = "String")
    private String status;

    /*
    新增的一些实体类
     */
    @TableField("sec_kill_inventory")
    @ApiModelProperty(name = "secKillInventory",value = "秒杀库存",dataType = "Integer",required = true)
    private String secKillInventory;

    @TableField("limited_purchase_quantity")
    @ApiModelProperty(name = "limitedPurchaseQuantity",value = "限购数量",dataType = "Integer",required = true)
    private String limitedPurchaseQuantity;

    @TableField("version")
    @ApiModelProperty(name ="version",value = "版本号",dataType = "Integer")
    private Integer version;
}

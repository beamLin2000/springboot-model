package com.gxa.modules.login.form;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gxa.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName("install")
public class BasicInstallForm implements Serializable {
    @TableField("time")
    @NotNull(message = "待付款订单取消时间不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "time",value = "取消时间",required = true)
    private Integer time;

    @TableField("time_type")
    @NotBlank(message = "时间格式不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "timeType",value = "时间格式",required = true)
    private String timeType;

    @TableField("currency_format")
    @NotBlank(message = "货币格式不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "currencyFormat",value = "货币格式",required = true)
    private String currencyFormat;

    @TableField("evaluate_status")
    @NotNull(message = "评价审核状态不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "evaluateStatus",value = "评价审核状态",required = true)
    private Integer evaluateStatus;

    @TableField("automatic_receipt")
    @NotNull(message = "自动收货状态不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "automaticReceipt",value = "自动收货状态",required = true)
    private Integer automaticReceipt;

    @TableField("receipt_time")
    @NotNull(message = "收货时长不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "receiptTime",value = "收货时长",required = true)
    private Integer receiptTime;

    @TableField("invoice_status")
    @NotNull(message = "能否开发票不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "invoiceStatus",value = "能否开发票",required = true)
    private Integer invoiceStatus;

    @TableField("user_agreement")
    @ApiModelProperty(name = "userAgreement",value = "用户协议")
    private String userAgreement;
}

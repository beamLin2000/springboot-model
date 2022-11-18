package com.gxa.modules.login.form;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gxa.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName("install")
public class BasicInstallForm implements Serializable {
    @TableField("time")
    @NotNull(message = "待付款订单取消时间不能为空",groups = UpdateGroup.class)
    private Integer time;

    @TableField("time_type")
    @NotBlank(message = "时间格式不能为空",groups = UpdateGroup.class)
    private String timeType;

    @TableField("currency_format")
    @NotBlank(message = "货币格式不能为空",groups = UpdateGroup.class)
    private String currencyFormat;

    @TableField("evaluate_status")
    @NotNull(message = "评价审核状态不能为空",groups = UpdateGroup.class)
    private Integer evaluateStatus;

    @TableField("automatic_receipt")
    @NotNull(message = "自动收货状态不能为空",groups = UpdateGroup.class)
    private Integer automaticReceipt;

    @TableField("receipt_time")
    @NotNull(message = "收货时长不能为空",groups = UpdateGroup.class)
    private Integer receiptTime;

    @TableField("invoice_status")
    @NotNull(message = "能否开发票不能为空",groups = UpdateGroup.class)
    private Integer invoiceStatus;

    @TableField("user_agreement")
    private String userAgreement;
}

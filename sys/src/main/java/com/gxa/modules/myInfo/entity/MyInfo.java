package com.gxa.modules.myInfo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("`order`")
public class MyInfo {
    private Integer phone;
    private String headPortrait;
    private Integer waitPay;//待支付
    private Integer waitOrder;//待发货
    private Integer waitConfirmReceipt;//待收货
    private Integer completedOrder;//已完成
    private Integer canceledOrder;//已取消
    private Integer refund;//退款
}

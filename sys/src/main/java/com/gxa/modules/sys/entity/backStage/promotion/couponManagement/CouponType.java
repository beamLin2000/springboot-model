package com.gxa.modules.sys.entity.backStage.promotion.couponManagement;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author LXD
 * @Date 2022/11/11 9:47
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponType {
    @ApiModelProperty(name = "couponType",value = "优惠券类型",dataType = "Array")
    private List<String> couponType;
}

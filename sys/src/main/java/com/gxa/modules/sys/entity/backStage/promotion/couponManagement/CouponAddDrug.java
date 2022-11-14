package com.gxa.modules.sys.entity.backStage.promotion.couponManagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LXD
 * @Date 2022/11/12 15:21
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponAddDrug {
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(name = "id",value = "id",dataType = "String")
    private String id;

    @TableField(value = "drug_code")
    @ApiModelProperty(name = "drugCode",value = "商品编号",dataType = "String")
    private String drugCode;

    @TableField(value = "drug_name")
    @ApiModelProperty(name = "drugName",value = "商品名称",dataType = "String")
    private String drugName;

}

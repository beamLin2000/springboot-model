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
 * @Date 2022/11/12 15:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponAddClass {
  @TableId(value = "id")
  @ApiModelProperty(name = "id",value = "id",dataType = "String")
  private String id;

  @TableField(value = "classification_name")
  @ApiModelProperty(name = "classificationName",value = "商品编号",dataType = "String")
  private String classificationName;

  @TableField(value = "level")
  @ApiModelProperty(name = "level",value = "商品编号",dataType = "String")
  private String level;

}

package com.gxa.modules.sys.entity.backStage.promotion.eventManagment;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LXD
 * @Date 2022/11/11 10:48
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("promotion_event_management")
public class EventManagement {

  @ApiModelProperty(name = "id",value = "uuid",dataType = "String")
  private String id;

  @TableField("activity_title")
  @ApiModelProperty(name = "activityTitle",value = "活动标题",dataType = "String")
  private String activityTitle;

  @TableField("list_chart")
  @ApiModelProperty(name = "listChart",value = "列表图",dataType = "String")
  private String listChart;

  @TableField("start_time")
  @ApiModelProperty(name = "startTime",value = "开始时间",dataType = "String")
  private String startTime;

  @TableField("end_time")
  @ApiModelProperty(name = "endTime",value = "结束时间",dataType = "String")
  private String endTime;

  @TableField("status")
  @ApiModelProperty(name = "status",value = "状态",dataType = "Integer")
  private Integer status;

  @TableField("put_on_shelves")
  @ApiModelProperty(name = "putOnShelves",value = "上架",dataType = "Integer")
  private Integer putOnShelves;

  /*
  新增产品
   */

  @TableField("activity_introduction")
  @ApiModelProperty(name = "activityIntroduction",value = "活动介绍",dataType = "String")
  private String activityIntroduction;
}

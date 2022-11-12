package com.gxa.modules.fristpage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_content_carousel")
public class PollGraph {
    @ApiModelProperty("轮播图图片地址")
    @TableField("jpg")
    private String url;
    @ApiModelProperty("顺序，数字越大越靠前")
    @TableField("sort")
    public  String seriation;
}

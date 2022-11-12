package com.gxa.modules.content.carousel.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//内容:首页轮播图
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_content_carousel")
public class Carousel {
    @ApiModelProperty(value = "id",required = false)
    private Integer id;//id
    @TableField("carousel_title")
    @ApiModelProperty(value = "标题",required = true)
    private String carouselTitle;//标题
    @ApiModelProperty(value = "图片地址",required = true)
    private String jpg;//图片
    @ApiModelProperty(value = "状态",required = true)
    private String state;//状态
    @ApiModelProperty(value = "时间",required = false)
    private Date date;
    @ApiModelProperty(value = "创建人",required = true)
    private String person;
    @ApiModelProperty(value = "数字排序",required = true)
    private Integer sort;

}

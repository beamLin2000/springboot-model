package com.gxa.modules.fristpage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitedTimeGoods {

    private String drugName;

    private String pictureUrl;

    private Double price;

    private Double discountPrice;

    private Date activityTime;

    private Date endTime;

    private String stock;
}

package com.gxa.modules.goods.goodsForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 症状分类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SymptomForm {

    private int id;
    private String symptomName;//症状名称
    private String rank;//级别
    private String state;//状态
    private String remarks;//备注
    private String upload;//上传人
    private Date addTime;//添加时间
    private int version;//版本号
}

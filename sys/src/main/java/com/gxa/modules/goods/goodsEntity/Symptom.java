package com.gxa.modules.goods.goodsEntity;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class Symptom {

    private String id;
    private String symptomName;//症状名称
    private String rank;//级别
    private String state;//状态
    private String remarks;//备注
    private String upload;//上传人
    private Date addTime;//添加时间
    private int version;//版本号
    @TableField("`higher_level`")
    private String higherLevel;//上级编号
}

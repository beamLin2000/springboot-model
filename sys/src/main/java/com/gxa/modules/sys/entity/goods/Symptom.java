package com.gxa.modules.sys.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Symptom {

    private int id;
    private String symptomName;//症状名称
    private String rank;//级别
    private String state;//状态
    private String remarks;//备注
    private String upload;//上传人
    private Date addTime;//添加时间
}

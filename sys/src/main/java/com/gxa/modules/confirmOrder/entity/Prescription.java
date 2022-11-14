package com.gxa.modules.confirmOrder.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//处方表
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    private Integer id;
    private String diagnosedDisease;//已确诊疾病
    private Integer usedDrug;//是否使用过该药品
    private Integer reactions;//有无不良反应
    private String medicalRecord;//病历信息图片地址
    private Integer drugUserInfoId;//关联用药人信息表
}

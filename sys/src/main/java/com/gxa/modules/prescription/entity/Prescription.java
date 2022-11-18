package com.gxa.modules.prescription.entity;

import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    @ApiModelProperty(name = "id",value = "id",dataType = "Integer",hidden = true)
    private Integer id;

    @ApiModelProperty(name = "diagnosedDisease",value = "已确诊疾病",dataType = "String",required = true)
    private String diagnosedDisease;//已确诊疾病


    @ApiModelProperty(name = "usedDrug",value = "是否使用过该药品",dataType = "Integer",required = true)
    private Integer usedDrug;//是否使用过该药品

    @ApiModelProperty(name = "reactions",value = "有无不良反应",dataType = "Integer",required = true)
    private Integer reactions;//有无不良反应

    @ApiModelProperty(name = "medicalRecord",value = "病历信息图片地址/原处方",dataType = "String",required = true)
    private String medicalRecord;//病历信息图片地址/原处方

    @ApiModelProperty(name = "drugUserInfoId",value = "把用药人的id放在这里",dataType = "Integer",required = true)
    private Integer drugUserInfoId;//关联用药人信息表

//    private DrugUserInfo drugUserInfo;

}

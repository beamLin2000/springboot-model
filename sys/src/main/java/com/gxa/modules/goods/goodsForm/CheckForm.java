package com.gxa.modules.goods.goodsForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckForm {
    private String drugName;//药品名称
    private String state;//审核状态 未通过，审核通过
    private String remarks;//备注

}

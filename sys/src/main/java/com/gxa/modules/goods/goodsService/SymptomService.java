package com.gxa.modules.goods.goodsService;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.goods.goodsEntity.Symptom;

import java.util.Map;

public interface SymptomService extends IService<Symptom> {

    PageUtils list(Map<String,Object> params);
    PageUtils listTwo(Map<String,Object> params);
    public void symptomUpdate(Symptom symptom);
    public void symptomInsert(Symptom symptom);
    public void symptomInsertRank(Symptom symptom);
    public void symptomTwoInsert(Symptom symptom);
    public void symptomTwoUpdate(Symptom symptom);

}

package com.gxa.modules.goods.goodsService;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.goods.goodsEntity.Medicinal;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface MedicinalService extends IService<Medicinal> {

    PageUtils list(Map<String,Object> params);
    PageUtils listTwo(Map<String,Object> params);

    public void medicinalUpdate(Medicinal medicinal, HttpServletRequest httpServletRequest);
    public void medicinalInsert(Medicinal medicinal);
    public void medicinalInsertRank(Medicinal medicinal);
    public void medicinalTwoInsert(Medicinal medicinal);
    public void medicinalTwoUpdate(Medicinal medicinal);
    public PageUtils medicinalSelect(Map<String,Object> params);

}

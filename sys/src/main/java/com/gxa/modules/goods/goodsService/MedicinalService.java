package com.gxa.modules.goods.goodsService;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.goods.goodsEntity.Medicinal;

import java.util.Map;

public interface MedicinalService extends IService<Medicinal> {

    PageUtils list(Map<String,Object> params);
    PageUtils listTwo(Map<String,Object> params);

}

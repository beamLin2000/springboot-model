package com.gxa.modules.goods.goodsService;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.goods.goodsEntity.Drug;

import java.util.Map;

public interface DrugService extends IService<Drug> {
    PageUtils list(Map<String,Object> params);
    PageUtils listCheck(Map<String,Object> params);
}

package com.gxa.modules.sys.service.goods;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.goods.Medicinal;

import java.util.List;
import java.util.Map;

public interface MedicinalService extends IService<Medicinal> {

    PageUtils List(Map<String,Object> params);

}

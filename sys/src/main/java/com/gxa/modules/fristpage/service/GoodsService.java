package com.gxa.modules.fristpage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.fristpage.entity.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService extends IService<Goods> {
    PageUtils queryByStr(Map<String,Object>param);
    PageUtils queryByin(Map<String,Object>param);
    PageUtils queryRecommed(Map<String,Object>param);
}

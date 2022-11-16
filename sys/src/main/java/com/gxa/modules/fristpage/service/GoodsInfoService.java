package com.gxa.modules.fristpage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.fristpage.entity.GoodsInfo;
import com.gxa.modules.fristpage.entity.GoodsInfodto;

import java.util.Date;

public interface GoodsInfoService extends IService<GoodsInfo> {
    GoodsInfodto queryByName(String name, Double price);
}

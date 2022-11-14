package com.gxa.modules.fristpage.service;

import com.gxa.modules.fristpage.entity.GoodsInfo;

import java.util.Date;

public interface GoodsInfoService {
    GoodsInfo queryByName(String name, Date fristDay,Date lastDay);
}

package com.gxa.modules.homepage.service.impl;

import com.gxa.modules.homepage.entity.CommodityPandent;
import com.gxa.modules.homepage.entity.UserPandent;
import com.gxa.modules.homepage.mapper.PandentMapper;
import com.gxa.modules.homepage.service.PandentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class PandentServiceImpl implements PandentService {

@Autowired
private PandentMapper pandentMapper;
    @Override
    public Map queryCommodityAndUserPandent(Date today, Date yesterday, Date tomorrow, Date firstday, Date lastday) {
        Integer commodityMonthNewAdded = this.pandentMapper.queryCommodityMonthNewAdded(firstday, lastday);
        Integer commodityTodayNewAdded = this.pandentMapper.queryCommodityTodayNewAdded(today,tomorrow);
        Integer commodityTotalGoodsNum = this.pandentMapper.queryCommodityTotalGoodsNum();
        Integer commodityYesterdayNewAdded = this.pandentMapper.queryCommodityYesterdayNewAdded(today,yesterday);
        Integer userTodayNewAdded = this.pandentMapper.queryUserTodayNewAdded(today,tomorrow);
        Integer userMonthNewAdded = this.pandentMapper.queryUserMonthNewAdded(firstday,lastday);
        Integer userTotalGoodsNum = this.pandentMapper.queryUserTotalGoodsNum();
        Integer userYesterdayNewAdded = this.pandentMapper.queryUserYesterdayNewAdded(today,yesterday);
        CommodityPandent commodityPandent = new CommodityPandent(commodityTodayNewAdded,
                commodityYesterdayNewAdded,commodityMonthNewAdded,commodityTotalGoodsNum);
        UserPandent userPandent = new UserPandent(userTodayNewAdded,userYesterdayNewAdded
                ,userMonthNewAdded,userTotalGoodsNum);
        Map map = new HashMap();
        map.put("commodityPandent",commodityPandent);
        map.put("userPandent",userPandent);
        return map;
    }
}

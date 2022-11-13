package com.gxa.modules.homepage.mapper;

import com.gxa.modules.homepage.entity.CommodityPandent;
import com.gxa.modules.homepage.entity.UserPandent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
@Mapper
public interface PandentMapper {
    Integer queryCommodityTodayNewAdded(@Param("today") Date today, @Param("tomorrow") Date tomorrow);
    Integer queryCommodityYesterdayNewAdded(@Param("today") Date today, @Param("yesterday") Date tomorrow);
    Integer queryCommodityMonthNewAdded(@Param("firstday")Date firstday,@Param("lastday")Date lastday);
    Integer queryCommodityTotalGoodsNum();

    Integer queryUserTodayNewAdded(@Param("today") Date today, @Param("tomorrow") Date tomorrow);
    Integer queryUserYesterdayNewAdded(@Param("today") Date today, @Param("yesterday") Date tomorrow);
    Integer queryUserMonthNewAdded(@Param("firstday")Date firstday,@Param("lastday")Date lastday);
    Integer queryUserTotalGoodsNum();
}

package com.gxa.modules.fristpage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.fristpage.entity.LimitedTimeGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Mapper
public interface LimitedTimeGoodsMapper extends BaseMapper<LimitedTimeGoods> {
    List<LimitedTimeGoods>  queryone();
    Integer queryStockByName(@Param("name") String name, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}

package com.gxa.modules.fristpage.mapper;

import com.gxa.modules.fristpage.entity.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
@Mapper
public interface GoodsInfoMapper {
    GoodsInfo queryByName(@Param("name") String name);
    Integer queryNum(@Param("name") String name,@Param("fristDay")Date fristDay,@Param("lastDay") Date lastDay);
}

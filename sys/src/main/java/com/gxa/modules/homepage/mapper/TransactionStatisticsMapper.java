package com.gxa.modules.homepage.mapper;

import com.gxa.modules.homepage.entity.TransactionStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TransactionStatisticsMapper {
   Double queryTransaction(@Param("today") Date today,@Param("tomorrow") Date tomorrow);
}

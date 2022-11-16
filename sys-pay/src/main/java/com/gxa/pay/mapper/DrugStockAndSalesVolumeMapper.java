package com.gxa.pay.mapper;


import com.gxa.pay.entity.DrugStockAndSalesVolume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrugStockAndSalesVolumeMapper {
    List<DrugStockAndSalesVolume> list(String orderNo);
    void updateStockAndSalesVolume(@Param("stock") Integer stock , @Param("salesVolume") Integer salesVolume,@Param("drugId") String drugId);

}

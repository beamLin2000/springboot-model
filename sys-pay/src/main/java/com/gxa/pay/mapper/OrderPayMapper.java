package com.gxa.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface OrderPayMapper  {
    Boolean updataForPay(@Param("payTime") Date payTime, @Param("orderNo")String orderNo);
    void updatePrescription(@Param("orderNo") String orderNo, @Param("status") Integer status);
}

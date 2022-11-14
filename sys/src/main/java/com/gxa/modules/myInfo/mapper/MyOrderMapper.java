package com.gxa.modules.myInfo.mapper;

import com.gxa.modules.myInfo.entity.OrderTemp;
import com.gxa.modules.myInfo.entity.WaitPayOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MyOrderMapper {
    List<WaitPayOrder> queryByStatus(@Param("id") Integer id, @Param("status") String status);
    List<OrderTemp> queryByIdAndStatus(@Param("orderNo") String orderNo);
}

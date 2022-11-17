package com.gxa.modules.order.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.order.dto.OrderDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetailDto> {
    OrderDetailDto queryOrderWaitDetailByOrderNo(@Param("orderNo") String orderNo);
    OrderDetailDto queryOrderCompleteDetailByOrderNo(@Param("orderNo") String orderNo);
    OrderDetailDto queryOrderCancelDetailByOrderNo(@Param("orderNo") String orderNo);
    OrderDetailDto queryOrderRefundDetailByOrderNo(@Param("orderNo") String orderNo);
    OrderDetailDto queryOrderWaitPayDetailByOrderNo(@Param("orderNo") String orderNo);
}

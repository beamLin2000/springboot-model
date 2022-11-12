package com.gxa.modules.order.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.order.dto.OrderDetailDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetailDto> {
    OrderDetailDto queryOrderDetailByOrderNo(String orderNo);
}

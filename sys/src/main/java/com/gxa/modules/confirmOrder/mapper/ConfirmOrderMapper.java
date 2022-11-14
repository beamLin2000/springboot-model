package com.gxa.modules.confirmOrder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.confirmOrder.dto.OrderDto;
import com.gxa.modules.confirmOrder.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfirmOrderMapper extends BaseMapper<OrderDto> {
}

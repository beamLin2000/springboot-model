package com.gxa.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.order.dto.OrderListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface DrugMapper extends BaseMapper<OrderListDto> {
        Page<OrderListDto> queryDrugOrderByPage(Page<OrderListDto> orderListDtoPage, @Param("orderStatus") String orderStatus, @Param("orderNo") String orderNo,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
        void updateDrugOrderByOrderNo(@Param("orderNo") String orderNo,@Param("expressDetailId") Integer expressDetailId);
}

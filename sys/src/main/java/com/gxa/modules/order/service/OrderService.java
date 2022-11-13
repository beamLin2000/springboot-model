package com.gxa.modules.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.order.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderService {
    Page<OrderListDto> queryDrugOrderByPage(Page<OrderListDto> orderListDtoPage, String orderStatus, String orderNo, Date startDate,Date endDate);
    Page<RefundListDto> queryRefundOrderByPage(Page<RefundListDto> refundListDtoPage,String refundStatus,String orderNo,Date startDate,Date endDate);
    OrderDetailDto queryOrderDetailByOrderNo(String orderNo);
    RefundDetailDto queryRefundDetailByApplicationNo(String applicationNo);
    List<ExpressDto> queryAllExpress();

}

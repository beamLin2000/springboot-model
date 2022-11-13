package com.gxa.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.order.dto.*;
import com.gxa.modules.order.mapper.DrugMapper;
import com.gxa.modules.order.mapper.ExpressMapper;
import com.gxa.modules.order.mapper.OrderDetailMapper;
import com.gxa.modules.order.mapper.RefundMapper;
import com.gxa.modules.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private DrugMapper drugMapper;

    @Autowired
    private RefundMapper refundMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public Page<OrderListDto> queryDrugOrderByPage(Page<OrderListDto> orderListDtoPage, String orderStatus, String orderNo, Date startDate,Date endDate) {
        Page<OrderListDto> orders = this.drugMapper.queryDrugOrderByPage(orderListDtoPage,orderStatus,orderNo,startDate,endDate);
        return orders;
    }

    @Override
    public Page<RefundListDto> queryRefundOrderByPage(Page<RefundListDto> refundListDtoPage, String refundStatus, String orderNo, Date startDate, Date endDate) {
        Page<RefundListDto> refundListDtoPage1 = this.refundMapper.queryRefundOrderByPage(refundListDtoPage, refundStatus, orderNo, startDate, endDate);
        return refundListDtoPage1;
    }

    @Override
    public OrderDetailDto queryOrderDetailByOrderNo(String orderNo) {
        OrderDetailDto orderDetailDto = this.orderDetailMapper.queryOrderDetailByOrderNo(orderNo);
        return orderDetailDto;
    }

    @Override
    public RefundDetailDto queryRefundDetailByApplicationNo(String applicationNo) {
        RefundDetailDto refundDetailDto = this.refundMapper.queryRefundDetailByApplicationNo(applicationNo);
        return refundDetailDto;
    }

    @Override
    public List<ExpressDto> queryAllExpress() {
        List<ExpressDto> expressDtos = this.expressMapper.selectList(null);
        return expressDtos;
    }
}

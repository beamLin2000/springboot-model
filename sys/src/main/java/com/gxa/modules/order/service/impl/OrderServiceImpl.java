package com.gxa.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.order.dto.*;
import com.gxa.modules.order.entity.ExpressDetail;
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

    @Override
    public void orderShipment(String orderNo, Date expressDate, Integer expressId,String expressNo) {
        ExpressDetail expressDetail = new ExpressDetail();
        expressDetail.setExpressDate(expressDate);
        expressDetail.setExpressId(expressId);
        expressDetail.setOrderNo(expressNo);
        this.expressMapper.insertExpressDetail(expressDetail);
        Integer expressDetailId = expressDetail.getId();

        this.drugMapper.updateDrugOrderByOrderNo(orderNo,expressDetailId);
    }

    @Override
    public ExpressDetail queryExpressDetailByOrderNo(String orderNo) {
        ExpressDetail expressDetail = this.expressMapper.selectExpressDetailByOrderNo(orderNo);
        return expressDetail;
    }

    @Override
    public void updateRefundStatusByApplicationNo(String applicationNo, String refundMark, String refundStatus) {
        String orderStatus = null;
        if (refundStatus.equals("已处理")){
            orderStatus="已完成";
        }else{
            orderStatus="已退款";
        }
        this.refundMapper.updateRefundStatusByApplicationNo(applicationNo,refundMark,refundStatus,orderStatus);
    }
}

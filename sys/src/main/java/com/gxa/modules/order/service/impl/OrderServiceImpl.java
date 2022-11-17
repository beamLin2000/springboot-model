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

import java.util.ArrayList;
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
    public OrderDetailDto queryOrderDetailByOrderNo(String orderNo,String orderStatus) {


        if (orderStatus.equals("待支付")){
          return this.orderDetailMapper.queryOrderWaitPayDetailByOrderNo(orderNo);
        }else if (orderStatus.equals("待发货")){
          return this.orderDetailMapper.queryOrderWaitDetailByOrderNo(orderNo);
        } else if (orderStatus.equals("待收货") || orderStatus.equals("已完成")){
          return this.orderDetailMapper.queryOrderCompleteDetailByOrderNo(orderNo);
        }else if (orderStatus.equals("已取消")){
          return this.orderDetailMapper.queryOrderCancelDetailByOrderNo(orderNo);
        }else if (orderStatus.equals("已退款")){
          return this.orderDetailMapper.queryOrderRefundDetailByOrderNo(orderNo);
        }

//        OrderDetailDto orderDetailDtos = this.orderDetailMapper.queryOrderCompleteDetailByOrderNo(orderNo);

        return null;
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
        expressDetail.setOrderNo(orderNo);
        expressDetail.setExpressNo(expressNo);
        this.expressMapper.insertExpressDetail(expressDetail);
        Integer expressDetailId = expressDetail.getId();

        this.drugMapper.updateDrugOrderByOrderNo(orderNo,expressDetailId);
    }

    @Override
    public List<ExpressDetailDto> queryExpressDetailByOrderNo(String orderNo) {
        List<ExpressDetailDto> expressDetail = this.expressMapper.selectExpressDetailByOrderNo(orderNo);
        return expressDetail;
    }

    @Override
    public void updateRefundStatusByApplicationNo(String applicationNo, String refundMark, String refundStatus,Date date) {
        String orderStatus = null;
        if (refundStatus.equals("已处理")){
            orderStatus="已完成";
        }else{
            orderStatus="已退款";
        }
        this.refundMapper.updateRefundStatusByApplicationNo(applicationNo,refundMark,refundStatus,orderStatus,date);
    }
}

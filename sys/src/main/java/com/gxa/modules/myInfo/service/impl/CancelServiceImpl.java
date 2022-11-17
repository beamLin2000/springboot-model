package com.gxa.modules.myInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.modules.myInfo.entity.RefundInfo;
import com.gxa.modules.myInfo.mapper.CancelMapper;
import com.gxa.modules.myInfo.service.CancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class CancelServiceImpl implements CancelService {
    @Autowired
    private CancelMapper cancelMapper;
    @Override
    public boolean updataStatus(String orderNo) {
        Date date = new Date();
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("order_no",orderNo);
        wrapper.set("order_status","已取消");
        wrapper.set("cancel_time",date);
        this.cancelMapper.update(null,wrapper);
        this.cancelMapper.updatePrescription(orderNo,2);
        return true;
    }

    @Override
    public boolean refund(String orderNo,String reason,Double price) {
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("order_no",orderNo);
        wrapper.set("application_no",orderNo);
        wrapper.set("refund_reason",reason);
        wrapper.set("refund_amount",price);
        wrapper.set("refund_status","待处理");
        wrapper.set("application_time",new Date());
        this.cancelMapper.update(null,wrapper);
        this.cancelMapper.updatePrescription(orderNo,7);
        return true;
    }

    @Override
    public boolean confirmOrder(String orderNo) {
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("order_no",orderNo);
        wrapper.set("order_status","已完成");
        this.cancelMapper.update(null,wrapper);
        this.cancelMapper.updatePrescription(orderNo,3);
        return true;
    }

    @Override
    public boolean delOrder(String orderNo) {

        this.cancelMapper.deleteByOrder(orderNo);
        this.cancelMapper.updatePrescription(orderNo,4);
        return false;
    }

    @Override
    public void rerefund(String orderNo) {
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("order_no",orderNo);
        wrapper.set("refund_status","撤销申请");
        this.cancelMapper.update(null,wrapper);
    }

    @Override
    public RefundInfo refunfInfo(String orderNo) {
        RefundInfo refundInfo = this.cancelMapper.refunfInfo(orderNo);
        return refundInfo;
    }
}

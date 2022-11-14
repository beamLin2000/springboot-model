package com.gxa.modules.myInfo.service.impl;

import com.gxa.modules.myInfo.entity.MyInfo;
import com.gxa.modules.myInfo.mapper.MyInfoMapper;
import com.gxa.modules.myInfo.service.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyInfoServiceImpl implements MyInfoService {
    @Autowired
    private MyInfoMapper myInfoMapper;
    @Override
    public MyInfo queryMyinfo(Integer id) {
        Integer waitPay = this.myInfoMapper.querywaitPay(id);
        Integer waitOrder = this.myInfoMapper.querywaitOrder(id);
        Integer waitConfirmReceipt = this.myInfoMapper.querywaitConfirmReceipt(id);
        Integer completedOrder = this.myInfoMapper.querycompletedOrder(id);
        Integer canceledOrder = this.myInfoMapper.querycanceledOrder(id);
        Integer refund = this.myInfoMapper.queryrefund(id);
        MyInfo myInfo = new MyInfo(null,null,waitPay,waitOrder,waitConfirmReceipt,completedOrder,canceledOrder,refund);
        return myInfo;
    }
}

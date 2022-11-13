package com.gxa.modules.homepage.service.impl;

import com.gxa.modules.homepage.entity.Pending;
import com.gxa.modules.homepage.mapper.PendingMapper;
import com.gxa.modules.homepage.service.PendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PendingServiceImpl implements PendingService {
    @Autowired
    private PendingMapper pendingMapper;
    @Override
    public Pending queryPending() {
        Integer waitOrder = this.pendingMapper.queryPendingWaitOrder();
        Integer waitRefund = this.pendingMapper.queryPendingWaitRefund();
        Integer inventoryAlert = this.pendingMapper.queryPendingInventoryAlert();
        Integer waitConfirmReceipt = this.pendingMapper.queryPendingWaitConfirmReceipt();
        Integer waitToExamine = this.pendingMapper.queryPendingWaitToExamine();
        Pending pending = new Pending(waitOrder,waitRefund,waitToExamine,waitConfirmReceipt,inventoryAlert);
        return pending;
    }
}

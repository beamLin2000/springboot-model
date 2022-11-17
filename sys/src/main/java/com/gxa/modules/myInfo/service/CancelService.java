package com.gxa.modules.myInfo.service;

import com.gxa.modules.myInfo.entity.RefundInfo;

public interface CancelService {
    boolean updataStatus(String orderNo);
    boolean refund(String orderNo,String reason,Double price);
    boolean confirmOrder(String orderNo);
    boolean delOrder(String orderNo);

    void rerefund(String orderNo);
    RefundInfo refunfInfo(String orderNo);
}

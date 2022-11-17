package com.gxa.modules.myInfo.service;

public interface CancelService {
    boolean updataStatus(String orderNo);
    boolean refund(String orderNo,String reason,Double price);
    boolean confirmOrder(String orderNo);
    boolean delOrder(String orderNo);
}

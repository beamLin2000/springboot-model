package com.gxa.modules.myInfo.service;

import com.gxa.modules.myInfo.entity.Coupon;

import java.util.List;

public interface CouponService {
    List<Coupon> queryByStatus(String userId, String status);
}

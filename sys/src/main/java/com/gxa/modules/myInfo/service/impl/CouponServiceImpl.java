package com.gxa.modules.myInfo.service.impl;

import com.gxa.modules.myInfo.entity.Coupon;
import com.gxa.modules.myInfo.mapper.UserCouponMapper;
import com.gxa.modules.myInfo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private UserCouponMapper userCouponMapper;
    @Override
    public  List<Coupon> queryByStatus(String userId, String status) {
        List<Coupon> coupons = this.userCouponMapper.queryByStatus(userId, status);
        return coupons;
    }
}

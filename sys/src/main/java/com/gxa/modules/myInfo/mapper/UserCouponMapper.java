package com.gxa.modules.myInfo.mapper;

import com.gxa.modules.myInfo.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCouponMapper {
    List<Coupon> queryByStatus(@Param("userId") String userId, @Param("status") String status);
}

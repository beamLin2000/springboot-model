package com.gxa.modules.sys.mapper.backStage.promotion.couponManagement;

import com.alipay.api.domain.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LXD
 * @Date 2022/11/11 15:43
 * @Version 1.0
 */
@Mapper
public interface CouponMapper extends BaseMapper<CouponManagement> {
    CouponManagementAll queryById(@Param("id") String id);

    void add(@Param("couponManagementAll") CouponManagerAddAndEdit couponManagementAll);

    void couponAddDrug(@Param("couponAddDrugs") List<CouponAddDrug> couponAddDrugs, @Param("id") String id);

    void couponAddClass(@Param("couponAddClasses") List<CouponAddClass> couponAddClasses, @Param("id") String id);

    List<CouponAddDrug> selectDrugById(@Param("id") String id);

    List<CouponAddClass> selectClassById(@Param("id") String id);

    void edit(CouponManagementAll couponManagementAll);

    void updataStatus(String id);

    void deleteDrugById(String id);

    void deleteClassById(String id);
}

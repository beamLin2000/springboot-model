package com.gxa.modules.sys.service.promotion;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagement;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagementAll;

import java.util.List;
import java.util.Map;

/**
 * @Author LXD
 * @Date 2022/11/11 15:37
 * @Version 1.0
 */
public interface CouponManagementService extends IService<CouponManagement> {
    PageUtils search(Map<String, Object> params);

    CouponManagementAll searchById(String id);

    void deleteByIds(List<String> ids);

    void add(CouponManagementAll couponManagementAll);

    CouponManagementAll selectById(String id);

    void edit(CouponManagementAll couponManagementAll);

     String addPre();
}

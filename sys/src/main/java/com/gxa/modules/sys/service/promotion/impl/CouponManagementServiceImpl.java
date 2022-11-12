package com.gxa.modules.sys.service.promotion.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponAddDrug;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagement;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagementAll;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponUsageInformation;
import com.gxa.modules.sys.entity.goods.Drug;
import com.gxa.modules.sys.mapper.backStage.promotion.couponManagement.CouponMapper;
import com.gxa.modules.sys.service.promotion.CouponManagementService;
import com.gxa.modules.sys.service.promotion.CouponUsageInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author LXD
 * @Date 2022/11/11 15:40
 * @Version 1.0
 */
@Service
public class CouponManagementServiceImpl extends ServiceImpl<CouponMapper, CouponManagement> implements CouponManagementService  {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponUsageInfoService couponUsageInfoService;

    /**
     * 根据条件查询，分页查询，模糊查询
     * @param params
     * @return
     */
    @Override
    public PageUtils search(Map<String, Object> params) {
        //获取条件
        String couponName = (String) params.get("couponName");
        String couponType = (String) params.get("couponType");

        IPage<CouponManagement> couponManagementIPage = this.page(new Query<CouponManagement>().getPage(params),
                new QueryWrapper<CouponManagement>().like(StringUtils.isNotEmpty(couponName), "coupon_name", couponName)
                        .like(StringUtils.isNotEmpty(couponType), "coupon_type", couponType).orderByDesc("id"));
        return new PageUtils(couponManagementIPage);
    }

    @Override
    public CouponManagementAll searchById(String id) {
        CouponManagementAll couponManagementAll = couponMapper.queryById(id);

        return couponManagementAll;
    }

    /**
     * 删除选中的数据
     * @param ids
     */
    public void deleteByIds(List<String> ids){
        int i = this.couponMapper.deleteBatchIds(ids);
    }

    @Override
    public void add(CouponManagementAll couponManagementAll) {
        this.couponMapper.add(couponManagementAll);


        List<CouponAddDrug> specifyProduct = couponManagementAll.getSpecifyProduct();
        this.couponMapper.couponAddDrug(specifyProduct);

    }


}

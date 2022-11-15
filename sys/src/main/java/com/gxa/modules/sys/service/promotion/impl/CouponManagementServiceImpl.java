package com.gxa.modules.sys.service.promotion.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.*;

import com.gxa.modules.sys.mapper.backStage.promotion.couponManagement.CouponMapper;
import com.gxa.modules.sys.service.promotion.CouponManagementService;
import com.gxa.modules.sys.service.promotion.CouponUsageInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author LXD
 * @Date 2022/11/11 15:40
 * @Version 1.0
 */
@Service
public class CouponManagementServiceImpl extends ServiceImpl<CouponMapper, CouponManagement> implements CouponManagementService {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponUsageInfoService couponUsageInfoService;

    /**
     * 根据条件查询，分页查询，模糊查询
     *
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
    public void deleteByIds(List<String> ids) {
        int i = this.couponMapper.deleteBatchIds(ids);
    }


    @Override
    public void add(CouponManagementAll couponManagementAll) {


        if (couponManagementAll.getUsableGoods().equals("指定商品")) {

            //调用查看商品的接口
            //调用商品的搜索接口
            //获取到页面传来的药品添加
            List<CouponAddDrug> specifyProduct = couponManagementAll.getSpecifyProduct();
            couponMapper.couponAddDrug(specifyProduct,couponManagementAll.getId());

        }else if (couponManagementAll.getUsableGoods().equals("指定分类")){

            //添加分类的优惠券
            List<CouponAddClass> specifyClassification = couponManagementAll.getSpecifyClassification();
            couponMapper.couponAddClass(specifyClassification,couponManagementAll.getId());

        }
        //保存优惠券的表
        this.couponMapper.add(couponManagementAll);

    }

    /**
     * 编辑前的查询
     * @param id
     */
    @Override
    public CouponManagementAll selectById(String id) {
        CouponManagementAll couponManagementAll = couponMapper.queryById(id);

        if (couponManagementAll.getUsableGoods().equals("指定商品")){
            List<CouponAddDrug> couponAddDrug = this.couponMapper.selectDrugById(id);

            couponManagementAll.setSpecifyProduct(couponAddDrug);
        }else if (couponManagementAll.getUsableGoods().equals("指定分类")){
            List<CouponAddClass> couponAddClass = this.couponMapper.selectClassById(id);
            couponManagementAll.setSpecifyClassification(couponAddClass);
        }
        return couponManagementAll;
    }

    /**
     * 编辑
     * @param couponManagementAll
     */
    @Override
    public void edit(CouponManagementAll couponManagementAll) {

        //编辑
        this.couponMapper.edit(couponManagementAll);

        if (couponManagementAll.getUsableGoods().equals("指定商品")){
            //删除添加过的药品
            this.couponMapper.deleteDrugById(couponManagementAll.getId());
        }else if(couponManagementAll.getUsableGoods().equals("指定分类")){
            //删除添加过的分类
            this.couponMapper.deleteClassById(couponManagementAll.getId());
        }

        //当他点击保存的时候
        if (couponManagementAll.getUsableGoods().equals("指定商品")) {

            //获取到页面传来的药品添加
            List<CouponAddDrug> specifyProduct = couponManagementAll.getSpecifyProduct();
            couponMapper.couponAddDrug(specifyProduct,couponManagementAll.getId());

        }else if (couponManagementAll.getUsableGoods().equals("指定分类")){

            //添加分类的优惠券
            List<CouponAddClass> specifyClassification = couponManagementAll.getSpecifyClassification();
            couponMapper.couponAddClass(specifyClassification,couponManagementAll.getId());
        }
    }

    @Override
    public  String addPre() {
        String s = UUID.randomUUID().toString();
        return s;
    }


}

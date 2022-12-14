package com.gxa.modules.goods.goodsService.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.Base64Utils;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.common.utils.RedisUtils;
import com.gxa.modules.goods.goodsEntity.Medicinal;
import com.gxa.modules.goods.goodsMapper.MedicinalMapper;
import com.gxa.modules.goods.goodsService.MedicinalService;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MedicinalServiceImpl extends ServiceImpl<MedicinalMapper, Medicinal> implements MedicinalService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserTokenService userTokenService;

//    @Autowired
//    private MedicinalService medicinalService;

    /**
     * 查询药品一级分类的所有数据
     * @param params 分页参数
     * @return
     */
    @Override
    public PageUtils list(Map<String, Object> params) {
        IPage<Medicinal> page = this.page(new Query<Medicinal>().getPage(params),
                new QueryWrapper<Medicinal>().eq("`rank`","一级"));

        return new PageUtils(page);
    }

    @Override
    public PageUtils listTwo(Map<String, Object> params) {
        IPage<Medicinal> page = this.page(new Query<Medicinal>().getPage(params),
                new QueryWrapper<Medicinal>().eq("`rank`","二级"));

        return new PageUtils(page);
    }

    public void medicinalUpdate(Medicinal medicinal, HttpServletRequest httpServletRequest){
        //删除Redis中的数据
        String categoryName = this.baseMapper.selectById(medicinal.getId()).getCategoryName();
        redisUtils.delete("Assort:"+ Base64Utils.encode(categoryName));


        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        medicinal.setUploader(sysUser.getUsername());

        Date date = new Date();
        medicinal.setAddTime(date);

        Map map = new HashMap();
        map.put("version",medicinal.getVersion());
        map.put("id",medicinal.getId());

        this.baseMapper.update(medicinal,new UpdateWrapper<Medicinal>().allEq(map));

        medicinal.setVersion(medicinal.getVersion()+1);
        this.baseMapper.update(medicinal,new UpdateWrapper<Medicinal>().eq("id",medicinal.getId()));
    }

    public void medicinalInsert(Medicinal medicinal){
        Date date = new Date();
        medicinal.setAddTime(date);

        medicinal.setRank("一级");
        this.baseMapper.insert(medicinal);

    }

    public void medicinalInsertRank(Medicinal medicinal){
        Date date = new Date();
        medicinal.setAddTime(date);
        medicinal.setRank("二级");

//        medicinal.setHigherLevel(medicinal.getId());
        //删除Redis中的数据
        redisUtils.delete("Assort:"+ Base64Utils.encode(medicinal.getCategoryName()));
        this.baseMapper.insert(medicinal);
    }

    public void medicinalTwoInsert(Medicinal medicinal){
        Date date = new Date();
        medicinal.setAddTime(date);
        medicinal.setRank("二级");
//        medicinal.setHigherLevel(medicinal.getId());
        //删除Redis中的数据
        redisUtils.delete("Assort:"+ Base64Utils.encode(medicinal.getCategoryName()));
        this.baseMapper.insert(medicinal);
    }

    public void medicinalTwoUpdate(Medicinal medicinal){
        //删除Redis中的数据
        redisUtils.delete("Assort:"+ Base64Utils.encode(medicinal.getCategoryName()));

        Date date = new Date();
        medicinal.setAddTime(date);

        Map map = new HashMap();
        map.put("version",medicinal.getVersion());
        map.put("id",medicinal.getId());

        this.baseMapper.update(medicinal,new UpdateWrapper<Medicinal>().allEq(map));

        medicinal.setVersion(medicinal.getVersion()+1);
        this.baseMapper.update(medicinal,new UpdateWrapper<Medicinal>().eq("id",medicinal.getId()));

    }

    public PageUtils medicinalSelect(Map<String,Object> params){
        IPage<Medicinal> page = this.page(new Query<Medicinal>().getPage(params),
                new QueryWrapper<Medicinal>().eq("`higher_level`", params.get("id")));
        return new PageUtils(page);
    }
}

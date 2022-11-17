package com.gxa.modules.sys.service.promotion.impl;

import com.gxa.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.backStage.promotion.timeLimitedSecondKill.LimitedTimeFlashDeal;
import com.gxa.modules.sys.mapper.backStage.promotion.timeLimitedSecondKill.TimeLimitedSecondKillMapper;
import com.gxa.modules.sys.service.promotion.TimeLimitedSecondKillService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author :林溪
 * @date : 2022/11/11 15:41
 */
@Service
public class TimeLimitedSecondKillImpl extends ServiceImpl<TimeLimitedSecondKillMapper, LimitedTimeFlashDeal> implements TimeLimitedSecondKillService{

    @Autowired
    private TimeLimitedSecondKillMapper timeLimitedSecondKillMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //定义分页数据类型用于接收
        PageUtils pageUtils = null;
        String drugName = (String) params.get("drugName");
        String status = (String) params.get("status");
            //mybatis plus 条件查询以及分页条件
            IPage<LimitedTimeFlashDeal> page = this.page(new Query<LimitedTimeFlashDeal>().getPage(params),
                    //添加条件,根据条件进行查询
                    new QueryWrapper<LimitedTimeFlashDeal>()
                            //drugName的模糊查询
                            .like(StringUtils.isNotEmpty(drugName), "drug_name", drugName)
                            //状态查询
                            .eq(StringUtils.isNotEmpty(status),"status",status)
                            //排序
                            .orderByDesc("id"));
            pageUtils = new PageUtils(page);
        return pageUtils;
    }

    @Override
    public LimitedTimeFlashDeal queryById(String id) {
        LimitedTimeFlashDeal limitedTimeFlashDeal = timeLimitedSecondKillMapper.selectById(id);
        return limitedTimeFlashDeal;
    }

    @Override
    public Integer deleteById(List<String> ids) {
        int i = timeLimitedSecondKillMapper.deleteBatchIds(ids);
        return i;
    }

    @Override
    public Integer saveData(LimitedTimeFlashDeal LimitedTimeFlashDeal) {
        int insert = timeLimitedSecondKillMapper.insert(LimitedTimeFlashDeal);
        return insert;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateData(LimitedTimeFlashDeal limitedTimeFlashDeal) throws Exception {
        Integer i = timeLimitedSecondKillMapper.update(limitedTimeFlashDeal,
                new QueryWrapper<LimitedTimeFlashDeal>().eq("version",limitedTimeFlashDeal.getVersion()));
        Integer i2 = timeLimitedSecondKillMapper.updateVersion(limitedTimeFlashDeal.getId());
        if(i==-1||i2==-1){
            throw new Exception("修改失败");
        }
        return i;
    }
}

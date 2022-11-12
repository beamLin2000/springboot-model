package com.gxa.modules.sys.service.promotion;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.backStage.promotion.timeLimitedSecondKill.LimitedTimeFlashDeal;

import java.util.List;
import java.util.Map;

/**
 * @author :林溪
 * @date : 2022/11/11 15:39
 */
public interface TimeLimitedSecondKillService extends IService<LimitedTimeFlashDeal> {
    //条件分页查询
    PageUtils queryPage(Map<String, Object> params);
    //根据id查询数据
    LimitedTimeFlashDeal queryById(Integer id);
    //根据id删除多条数据
    Integer deleteById(List<String> ids);
    //新增
    Integer saveData(LimitedTimeFlashDeal limitedTimeFlashDeal);
    //编辑
    Integer updateData(LimitedTimeFlashDeal limitedTimeFlashDeal);
}

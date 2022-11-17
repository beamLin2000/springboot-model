package com.gxa.modules.sys.mapper.backStage.promotion.timeLimitedSecondKill;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.backStage.promotion.timeLimitedSecondKill.LimitedTimeFlashDeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author :林溪
 * @date : 2022/11/11 15:37
 */
@Mapper
public interface TimeLimitedSecondKillMapper extends BaseMapper<LimitedTimeFlashDeal> {
    Integer updateVersion(@Param("id")String id);
}

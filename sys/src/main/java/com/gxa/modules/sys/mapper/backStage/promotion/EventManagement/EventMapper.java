package com.gxa.modules.sys.mapper.backStage.promotion.EventManagement;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.backStage.promotion.eventManagment.EventManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author :林溪
 * @date : 2022/11/12 10:09
 */
@Mapper
public interface EventMapper extends BaseMapper<EventManagement> {
    Integer updateStatus(@Param("id") String id,
                         @Param("status")Integer status);
}

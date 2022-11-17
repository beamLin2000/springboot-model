package com.gxa.modules.sys.mapper.backStage.promotion.EventManagement;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.backStage.promotion.eventManagment.EventManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :林溪
 * @date : 2022/11/12 10:09
 */
@Mapper
public interface EventMapper extends BaseMapper<EventManagement> {
    Integer updateStatus(@Param("id") String id,
                         @Param("status")Integer status,
                         @Param("version")Integer version);

    //查询所有id
    List<String> selectAllId();
    //根据id与version查询
    //EventManagement queryByIdAndVersion(String id,Integer version);
}

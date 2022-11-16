package com.gxa.modules.sys.service.promotion;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.backStage.promotion.eventManagment.EventManagement;

import java.util.List;
import java.util.Map;

/**
 * @author :林溪
 * @date : 2022/11/12 10:08
 */
public interface EventService extends IService<EventManagement> {
    //搜索/分页
    PageUtils search(Map<String,Object> map);
    //上架状态修改
    Integer updateStatus(String id,Integer status,Integer version);
    //根据id查询数据
    EventManagement selectById(String id);
    //删除数据根据id
    Integer deleteByIds(List<String> id);
    //保存数据
    Integer saveData(EventManagement eventManagement);
    //修改数据
    Integer updateData(EventManagement eventManagement);
    //根据id与版本号查询单一数据
    EventManagement queryByIdAndVersion(String id,Integer version);

}

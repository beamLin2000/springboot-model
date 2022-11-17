package com.gxa.modules.sys.service.promotion.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.sys.entity.backStage.promotion.eventManagment.EventManagement;
import com.gxa.modules.sys.mapper.backStage.promotion.EventManagement.EventMapper;
import com.gxa.modules.sys.service.promotion.EventService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author :林溪
 * @date : 2022/11/12 10:08
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, EventManagement> implements EventService {
    @Autowired
    private EventMapper eventMapper;
    @Override
    public PageUtils search(Map<String, Object> map) {
        String activeTitle = (String)map.get("activityTitle");
        String status = "全部".equals((String)map.get("status"))?null:(String)map.get("status");
        IPage page = this.page(new Query<EventManagement>().getPage(map),
                                new QueryWrapper<EventManagement>().like(StringUtils.isNotEmpty(activeTitle),"activity_title",activeTitle)
                                .eq(StringUtils.isNotEmpty(status),"status",status));

        return new PageUtils(page);
    }

    @Override
    public Integer updateStatus(String id,Integer status,Integer version) {
        Integer integer = eventMapper.updateStatus(id,status==1?0:1,version);
        return integer;
    }

    @Override
    public EventManagement selectById(String id) {
        EventManagement eventManagement = eventMapper.selectById(id);
        return eventManagement;
    }

    @Override
    public Integer deleteByIds(List<String> id) {
        return eventMapper.deleteBatchIds(id);
    }

    @Override
    public Integer saveData(EventManagement eventManagement) {
        List<String> strings = eventMapper.selectAllId();
        if(strings.contains(eventManagement.getId())){
            return -1;
        }
        return eventMapper.insert(eventManagement);
    }

    @Override
    public Integer updateData(EventManagement eventManagement) {
        EventManagement eventManagement1 = selectById(eventManagement.getId());
        if(eventManagement1!=null&&eventManagement.getVersion()!=eventManagement1.getVersion()){
            return -1;
        }
        eventManagement.setVersion(eventManagement.getVersion()+1);
        return eventMapper.update(eventManagement,new QueryWrapper<EventManagement>().eq("id",eventManagement.getId()).eq("version",eventManagement.getVersion()-1));
    }

    @Override
    public EventManagement queryByIdAndVersion(String id, Integer version) {
        return eventMapper.selectOne(new QueryWrapper<EventManagement>().eq("id",id)
                                    .eq("version",version));
    }


}

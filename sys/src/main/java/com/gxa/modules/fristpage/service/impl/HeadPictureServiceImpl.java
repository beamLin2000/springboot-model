package com.gxa.modules.fristpage.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.modules.fristpage.mapper.HeadPictureMapper;
import com.gxa.modules.fristpage.service.HeadPictureService;
import com.gxa.modules.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeadPictureServiceImpl implements HeadPictureService {
    @Autowired
    private HeadPictureMapper headPictureMapper;
    @Override
    public void update(Integer id,String str) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("head_portrait",str);
        updateWrapper.eq("id",id);
        this.headPictureMapper.update(null,updateWrapper);
    }
}

package com.gxa.modules.login.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.login.form.BasicInstallForm;
import com.gxa.modules.login.mapper.BasicInstallMapper;
import com.gxa.modules.login.service.BasicInstallService;
import org.springframework.stereotype.Service;

@Service
public class BasicInstallServiceImpl extends ServiceImpl<BasicInstallMapper, BasicInstallForm> implements BasicInstallService {
    @Override
    public BasicInstallForm queryAll() {
        return this.baseMapper.selectOne(new UpdateWrapper<BasicInstallForm>().eq("id",1));
    }
}

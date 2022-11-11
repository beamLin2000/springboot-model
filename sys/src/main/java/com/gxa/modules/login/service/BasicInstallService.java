package com.gxa.modules.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.login.form.BasicInstallForm;

public interface BasicInstallService extends IService<BasicInstallForm> {
    BasicInstallForm queryAll();
}

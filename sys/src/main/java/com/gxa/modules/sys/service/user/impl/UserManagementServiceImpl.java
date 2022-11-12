package com.gxa.modules.sys.service.user.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.backStage.user.UserManagement;
import com.gxa.modules.sys.mapper.backStage.user.UserManagementMapper;
import com.gxa.modules.sys.service.user.UserManagementService;
import org.springframework.stereotype.Service;

/**
 * @author :林溪
 * @date : 2022/11/12 15:23
 */
@Service
public class UserManagementServiceImpl extends ServiceImpl<UserManagementMapper, UserManagement> implements UserManagementService {

}

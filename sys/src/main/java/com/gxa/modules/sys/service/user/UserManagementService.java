package com.gxa.modules.sys.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.backStage.user.Address;
import com.gxa.modules.sys.entity.backStage.user.DrugUserInformation;
import com.gxa.modules.sys.entity.backStage.user.UserManagement;

import java.util.List;
import java.util.Map;

/**
 * @author :林溪
 * @date : 2022/11/12 15:23
 */
public interface UserManagementService extends IService<UserManagement> {
    //搜索功能/首页列表
    PageUtils search(Map<String,Object> map);

    //批量删除
    Integer deleteByIds(List<String> ids) throws Exception;

    //状态按钮修改
    Integer updateStatus(Integer id,Integer status,Integer version);
}

package com.gxa.modules.sys.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.backStage.user.Address;

import java.util.List;

/**
 * @author :林溪
 * @date : 2022/11/12 15:34
 */
public interface AddressService extends IService<Address> {
    //查看收货地址
    List<Address> queryAddress(String id);
    //

}

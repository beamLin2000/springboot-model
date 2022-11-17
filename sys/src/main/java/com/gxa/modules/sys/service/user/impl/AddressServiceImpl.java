package com.gxa.modules.sys.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.backStage.user.Address;
import com.gxa.modules.sys.entity.backStage.user.UserManagement;
import com.gxa.modules.sys.mapper.backStage.user.AddressMapper;
import com.gxa.modules.sys.service.user.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :林溪
 * @date : 2022/11/12 15:34
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<Address> queryAddress(String id) {
        List<Address> t_user_id = addressMapper.selectList(new QueryWrapper<Address>().eq(true, "t_user_id", id).orderByDesc("id"));
        return t_user_id;
    }

}

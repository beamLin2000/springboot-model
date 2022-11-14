package com.gxa.modules.myInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxa.modules.myInfo.entity.ShipToAddress;
import com.gxa.modules.myInfo.mapper.NewAddressMapper;
import com.gxa.modules.myInfo.service.NewAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewAddressServiceImpl implements NewAddressService {
    @Autowired
    private NewAddressMapper newAddressMapper;
    @Override
    public boolean addAddress(ShipToAddress shipToAddress) {
        this.newAddressMapper.insert(shipToAddress);
        return true;
    }

    @Override
    public List<ShipToAddress> allAddress(String id) {
        List<ShipToAddress> shipToAddresses = this.newAddressMapper.selectList(new QueryWrapper<ShipToAddress>().eq("t_user_id",id));
        return shipToAddresses;
    }

}

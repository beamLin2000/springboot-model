package com.gxa.modules.myInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        if (shipToAddress.getIsDefault().equals("1")){
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.set("default_address","0");
            wrapper.eq("default_address","1");
            this.newAddressMapper.update(null,wrapper);
        }
        this.newAddressMapper.insert(shipToAddress);
        return true;
    }

    @Override
    public List<ShipToAddress> allAddress(String id) {
        List<ShipToAddress> shipToAddresses = this.newAddressMapper.selectList(new QueryWrapper<ShipToAddress>().eq("t_user_id",id));
        return shipToAddresses;
    }

    @Override
    public ShipToAddress editPreAddress(String id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        ShipToAddress shipToAddress = this.newAddressMapper.selectOne(wrapper);
        return shipToAddress;
    }

    @Override
    public void editAddress(ShipToAddress shipToAddress) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        if (shipToAddress.getIsDefault().equals("1")){
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.set("default_address","0");
            wrapper.eq("default_address","1");
            this.newAddressMapper.update(null,wrapper);
        }
        updateWrapper.eq("id",shipToAddress.getId());
        this.newAddressMapper.update(shipToAddress,updateWrapper);

    }

    @Override
    public void delAddress(String id) {
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("id",id);
        this.newAddressMapper.delete(wrapper);
    }

}

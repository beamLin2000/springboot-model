package com.gxa.modules.myInfo.service;

import com.gxa.modules.myInfo.entity.ShipToAddress;

import java.util.List;

public interface NewAddressService {
    boolean addAddress(ShipToAddress shipToAddress);
    List<ShipToAddress> allAddress(String id);
}

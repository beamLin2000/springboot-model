package com.gxa.modules.sys.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.backStage.user.Address;
import com.gxa.modules.sys.mapper.backStage.user.AddressMapper;
import com.gxa.modules.sys.service.user.AddressService;
import org.springframework.stereotype.Service;

/**
 * @author :林溪
 * @date : 2022/11/12 15:34
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}

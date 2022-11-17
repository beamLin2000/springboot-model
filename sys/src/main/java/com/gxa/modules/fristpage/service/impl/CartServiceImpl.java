package com.gxa.modules.fristpage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.fristpage.entity.Cart;
import com.gxa.modules.fristpage.mapper.CartMapper;
import com.gxa.modules.fristpage.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
}

package com.gxa.modules.sys.service;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserTokenService {
    Result createToken(User user);

    User validateToken(String token);
}

package com.empmanagesys.security;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户登录密码混淆类
 */
public class UserPasswordSaltSource implements SaltSource {

    @Override
    public Object getSalt(UserDetails user) {
        return user.getUsername();
    }
}

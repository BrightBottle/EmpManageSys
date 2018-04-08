
package com.empmanagesys.module.login.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empmanagesys.core.BaseService;
import com.empmanagesys.module.login.dao.LoginDao;


/**
 * 登录处理 Service 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Service
@Transactional
public class LoginService extends BaseService {

    // 注入登录处理Dao
    @Autowired
    private LoginDao loginDao;
}

package com.empmanagesys.module.login.dao;

import org.springframework.stereotype.Repository;

import com.empmanagesys.core.BaseHibernateDao;
import com.empmanagesys.model.UserModel;


/**
 * 登录处理 Dao 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Repository
public class LoginDao extends BaseHibernateDao<UserModel, Long> {

}

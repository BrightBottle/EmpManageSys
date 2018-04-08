
package com.empmanagesys.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empmanagesys.model.UserModel;
import com.empmanagesys.security.SecurityHelper;


/**
 * Service 基类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
public abstract class BaseService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 获取当前登录用户信息
	 * 
	 * @return
	 */
	protected UserModel getCurrentUser() {
		return SecurityHelper.getCurrentUser();
	}
}

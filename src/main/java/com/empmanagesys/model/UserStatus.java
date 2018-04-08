package com.empmanagesys.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 用户状态
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Entity
@DiscriminatorValue(value = "com.empmanagesys.model.UserStatus")
public class UserStatus extends DictionaryModel {

    private static final long serialVersionUID = 1L;

    // 正常状态
    public static final UserStatus activeStatus = new UserStatus("USRS_Active");

    // 禁用状态
    public static final UserStatus disabledStatus = new UserStatus("USRS_Disabled");

    public UserStatus() {
    }

    public UserStatus(String code) {
        super.setCode(code);
    }
}

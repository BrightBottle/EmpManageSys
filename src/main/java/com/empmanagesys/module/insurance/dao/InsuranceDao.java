package com.empmanagesys.module.insurance.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.empmanagesys.core.BaseHibernateDao;
import com.empmanagesys.model.Insurance;

/**
 * 保险管理 Dao 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Repository
public class InsuranceDao extends BaseHibernateDao<Insurance, Long>{

    /**
     * 获得保险信息
     * @return
     */
    public List<Insurance> getAllInsurance() {
    	return getAll();
    }
}

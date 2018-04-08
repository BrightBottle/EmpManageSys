package com.empmanagesys.module.salary.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.empmanagesys.core.BaseHibernateDao;
import com.empmanagesys.core.PageContainer;
import com.empmanagesys.model.SalaryStandard;

/**
 * 标准薪资管理 Dao 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Repository
public class SalaryStandardDao extends BaseHibernateDao<SalaryStandard, Long>{

	

    /**
     * 获取标准薪资分页信息
     * 
     * @return
     */
    public PageContainer getSalaryStandardPage(Map<String, String> params) {
        // 创建根据条件模糊查询条件
        Criterion empId = createLikeCriterion("empId", "%" + params.get("empId") + "%");
        return getDataPage(params, empId);
    }
    
    /**
     * 获得所有标准薪资信息
     * @return
     */
    public List<SalaryStandard> getAllSalaryStandard() {
    	return getAll();
    }
    
    /**
     * 根据条件查询标准薪资信息
     * 
     * @param loginName 登录名称
     * @return
     */
    public SalaryStandard getSalaryStandard(String name) {
        Criterion emailValue = createCriterion("name", name);
        return findUnique(emailValue);
    }
}

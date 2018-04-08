package com.empmanagesys.module.department.dao;

import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.empmanagesys.core.BaseHibernateDao;
import com.empmanagesys.core.PageContainer;
import com.empmanagesys.model.DepartmentModel;

/**
 * 部门管理 Dao 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Repository
public class DepartmentDao extends BaseHibernateDao<DepartmentModel, Long>{

	

    /**
     * 获取部门分页信息
     * 
     * @return
     */
    public PageContainer getDepartmentPage(Map<String, String> params) {
        // 创建根据部门名称模糊查询条件
        Criterion name = createLikeCriterion("name", "%" + params.get("name") + "%");
        return getDataPage(params, name);
    }

}

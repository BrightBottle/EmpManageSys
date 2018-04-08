package com.empmanagesys.module.attendence.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.empmanagesys.core.BaseHibernateDao;
import com.empmanagesys.core.PageContainer;
import com.empmanagesys.model.Attendence;

/**
 * 考勤管理 Dao 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Repository
public class AttendenceDao extends BaseHibernateDao<Attendence, Long>{

	

    /**
     * 获取考勤信息分页信息
     * 
     * @return
     */
    public PageContainer getAttendencePage(Map<String, String> params) {
        // 创建根据条件模糊查询条件
        Criterion empId = createLikeCriterion("empId", "%" + params.get("empId") + "%");
        Criterion year = createLikeCriterion("year", "%" + params.get("year") + "%");
        Criterion month = createLikeCriterion("month", "%" + params.get("month") + "%");
        return getDataPage(params, empId,year,month);
    }
    
    /**
     * 获得所有考勤信息
     * @return
     */
    public List<Attendence> getAllAttendence() {
    	return getAll();
    }
    /**
     * 根据条件查询考勤信息
     * 
     * @param loginName 登录名称
     * @return
     */
    public Attendence getAttendence(String name) {
        Criterion emailValue = createCriterion("name", name);
        return findUnique(emailValue);
    }
    
}

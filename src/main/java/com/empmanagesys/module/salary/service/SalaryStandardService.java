package com.empmanagesys.module.salary.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empmanagesys.core.BaseService;
import com.empmanagesys.core.PageContainer;
import com.empmanagesys.model.SalaryStandard;
import com.empmanagesys.module.salary.dao.SalaryStandardDao;
import com.empmanagesys.util.DateUtils;

/**
 * 薪资管理 Service 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Service
//当标于类前时, 标示类中所有方法都进行事物处理
@Transactional
public class SalaryStandardService extends BaseService{
	

    // 注入考勤信息处理Dao
    @Autowired
    private SalaryStandardDao salaryStandardDao;
    
    /**
     * 获取标准薪资信息分页信息
     * 
     * @return
     */
    public PageContainer getSalaryStandardPage(Map<String, String> params) {
        return salaryStandardDao.getSalaryStandardPage(params);
    }

    /**
     * 根据Id获取标准薪资信息
     * 
     * @return
     */
    public SalaryStandard getSalaryStandard(Long salaryStandardId) {
        return salaryStandardDao.get(salaryStandardId);
    }

    /**
     * 保存考勤信息
     * 
     * @return
     */
    public void saveSalaryStandard(SalaryStandard salaryStandard) {
        // 设置最后更新时间
    	salaryStandard.setUpdateTime(DateUtils.timeToString(new Date()));
        salaryStandardDao.save(salaryStandard);
    }
  
    /**
     * 根据Id删除标准薪资信息
     * 
     * @return
     */
    public void deleteSalaryStandard(Long salaryStandardId) {
    	salaryStandardDao.delete(salaryStandardId);
    }

    /**
     * 查询所有标准薪资信息
     * 
     * @return
     */
    public List<SalaryStandard> getSalaryStandard() {
        return salaryStandardDao.find();
    }

    /**
     * 查询所有标准薪资信息
     * 
     * @return
     */
    public List<SalaryStandard> getAll() {
        return salaryStandardDao.getAllSalaryStandard();
    }
    
    /**
     * 根据用户名，获取用户标准薪资信息
     * 
     * @param userId
     * @return
     */
   
	public SalaryStandard getSalaryStandard(String name) {
        SalaryStandard salaryStandard = salaryStandardDao.getSalaryStandard(name);
        return salaryStandard;
    }
    
}

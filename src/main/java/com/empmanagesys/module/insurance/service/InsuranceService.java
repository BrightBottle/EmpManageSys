package com.empmanagesys.module.insurance.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empmanagesys.core.BaseService;
import com.empmanagesys.model.Insurance;
import com.empmanagesys.module.insurance.dao.InsuranceDao;
import com.empmanagesys.util.DateUtils;

/**
 * 保险管理 Service 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Service
//当标于类前时, 标示类中所有方法都进行事物处理
@Transactional
public class InsuranceService extends BaseService{
	

    // 注入保险信息处理Dao
    @Autowired
    private InsuranceDao insuranceDao;
  
    /**
     * 根据Id获取保险设置信息
     * 
     * @return
     */
    public Insurance getInsuranceById(Long insuranceId) {
        return insuranceDao.get(insuranceId);
    }

    
    /**
     * 保存保险设置信息
     * 
     * @return
     */
    public void saveInsurance(Insurance insurance) {
        // 设置最后更新时间
    	insurance.setUpdateTime(DateUtils.timeToString(new Date()));
    	insuranceDao.save(insurance);
    }
    /**
     * 查询所有保险设置信息
     * 
     * @return
     */
    public List<Insurance> getInsurance() {
        return insuranceDao.getAllInsurance();
    }
}

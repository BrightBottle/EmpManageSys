package com.empmanagesys.module.attendence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empmanagesys.core.BaseHibernateDao;
import com.empmanagesys.model.AttendenceSet;

/**
 * 考勤奖罚管理 Dao 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Repository
public class AttendenceSetDao extends BaseHibernateDao<AttendenceSet, Long>{

    /**
     * 获得考勤奖罚信息
     * @return
     */
    public List<AttendenceSet> getAllAttendence() {
    	return getAll();
    }
}

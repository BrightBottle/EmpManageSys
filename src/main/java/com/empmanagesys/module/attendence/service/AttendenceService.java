package com.empmanagesys.module.attendence.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empmanagesys.core.BaseService;
import com.empmanagesys.core.PageContainer;
import com.empmanagesys.model.Attendence;
import com.empmanagesys.model.AttendenceSet;
import com.empmanagesys.module.attendence.dao.AttendenceDao;
import com.empmanagesys.module.attendence.dao.AttendenceSetDao;
import com.empmanagesys.util.DateUtils;

/**
 * 考勤管理 Service 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Service
//当标于类前时, 标示类中所有方法都进行事物处理
@Transactional
public class AttendenceService extends BaseService{
	

    // 注入考勤信息处理Dao
    @Autowired
    private AttendenceDao attendenceDao;

 // 注入考勤设置信息处理Dao
    @Autowired
    private AttendenceSetDao attendenceSetDao;

    /**
     * 获取考勤信息分页信息
     * 
     * @return
     */
    public PageContainer getAttendencePage(Map<String, String> params) {
        return attendenceDao.getAttendencePage(params);
    }

    /**
     * 根据Id获取考勤信息
     * 
     * @return
     */
    public Attendence getAttendence(Long attendenceId) {
        return attendenceDao.get(attendenceId);
    }

    /**
     * 根据Id获取考勤设置信息
     * 
     * @return
     */
    public AttendenceSet getAttendenceSet(Long attendenceSetId) {
        return attendenceSetDao.get(attendenceSetId);
    }
    /**
     * 保存考勤信息
     * 
     * @return
     */
    public void saveAttendence(Attendence attendence) {
        // 设置最后更新时间
        attendence.setUpdateTime(DateUtils.timeToString(new Date()));
        attendenceDao.save(attendence);
    }
    
    /**
     * 保存考勤设置信息
     * 
     * @return
     */
    public void saveAttendenceSet(AttendenceSet attendenceSet) {
        // 设置最后更新时间
    	attendenceSet.setUpdateTime(DateUtils.timeToString(new Date()));
        attendenceSetDao.save(attendenceSet);
    }

    /**
     * 根据Id删除考勤信息
     * 
     * @return
     */
    public void deleteAttendence(Long attendenceId) {
    	attendenceDao.delete(attendenceId);
    }

    /**
     * 查询所有考勤信息
     * 
     * @return
     */
    public List<Attendence> getAllattendences() {
        return attendenceDao.find();
    }

    /**
     * 查询所有考勤信息
     * 
     * @return
     */
    public List<Attendence> getAll() {
        return attendenceDao.getAllAttendence();
    }
    
    /**
     * 查询所有考勤设置信息
     * 
     * @return
     */
    public List<AttendenceSet> getSet() {
        return attendenceSetDao.getAllAttendence();
    }
    
    /**
     * 根据用户名，获取用户考勤信息
     * 
     * @param name
     * @return
     */
   
	public Attendence getAttendence(String name) {
        Attendence attendence = attendenceDao.getAttendence(name);
        return attendence;
    }


//最终考勤奖罚

    /**
     * 整合考勤奖罚信息
     * @param name
     * @return
     */
    public Double[] finalAttendenceMoney(String name){
        List<Attendence> attendence = new ArrayList<>();
		Attendence attendences = attendenceDao.getAttendence(name);
		attendence.add(attendences);

    	List<AttendenceSet> attendenceSet = attendenceSetDao.getAllAttendence();

        Integer lateCome = null;
    	Double lateComeSet=null;
    	Integer earlyLeave = null;
    	Double earlyLeaveSet=null;
    	Integer vacate = null;
    	Double vacateSet=null;
    	Integer overtime = null;
    	Double overtimeSet=null;
    	Integer negletwork = null;
    	Double negletworkSet=null;
    	for (Attendence attendenceInfo : attendence) {
    		 lateCome = attendenceInfo.getLateCome();
    		 earlyLeave = attendenceInfo.getEarlyLeave();
    		 vacate = attendenceInfo.getVacate();
    		 overtime=attendenceInfo.getOvertime();
    		 negletwork= attendenceInfo.getNegletwork();

    	}
		for (AttendenceSet attendenceSetInfo : attendenceSet) {
			lateComeSet = attendenceSetInfo.getLateCome();
			 earlyLeaveSet = attendenceSetInfo.getEarlyLeave();
    		 vacateSet = attendenceSetInfo.getVacate();
    		 overtimeSet =attendenceSetInfo.getOvertime();
    		 negletworkSet = attendenceSetInfo.getNegletwork();
		}
		Double sumLateCome = (lateCome*lateComeSet);
		Double sumEarlyLeave = (earlyLeave*earlyLeaveSet);
		Double sumVacate = (vacate*vacateSet);
		Double sumOvertime = (overtime*overtimeSet);
		Double sumNegletwork = (negletwork*negletworkSet);

        Double[] finalAttendenceMoney = new Double[5];
        finalAttendenceMoney[0] = sumLateCome;
        finalAttendenceMoney[1] = sumEarlyLeave;
        finalAttendenceMoney[2] = sumVacate;
        finalAttendenceMoney[3] = sumOvertime;
        finalAttendenceMoney[4] = sumNegletwork;
	    return finalAttendenceMoney;
    }

    /**
     * 整合所有考勤奖罚信息
     * @param
     * @return
     */
    public Double[][] AllFinalAttendenceMoney() {
        List<Attendence> attendence = attendenceDao.getAll();

        List<AttendenceSet> attendenceSet = attendenceSetDao.getAllAttendence();

        Integer lateCome = null;
        Double lateComeSet = null;
        Integer earlyLeave = null;
        Double earlyLeaveSet = null;
        Integer vacate = null;
        Double vacateSet = null;
        Integer overtime = null;
        Double overtimeSet = null;
        Integer negletwork = null;
        Double negletworkSet = null;
        for (Attendence attendenceInfo : attendence) {
            lateCome = attendenceInfo.getLateCome();
            earlyLeave = attendenceInfo.getEarlyLeave();
            vacate = attendenceInfo.getVacate();
            overtime = attendenceInfo.getOvertime();
            negletwork = attendenceInfo.getNegletwork();

        }
        for (AttendenceSet attendenceSetInfo : attendenceSet) {
            lateComeSet = attendenceSetInfo.getLateCome();
            earlyLeaveSet = attendenceSetInfo.getEarlyLeave();
            vacateSet = attendenceSetInfo.getVacate();
            overtimeSet = attendenceSetInfo.getOvertime();
            negletworkSet = attendenceSetInfo.getNegletwork();
        }
        Double sumLateCome = (lateCome * lateComeSet);
        Double sumEarlyLeave = (earlyLeave * earlyLeaveSet);
        Double sumVacate = (vacate * vacateSet);
        Double sumOvertime = (overtime * overtimeSet);
        Double sumNegletwork = (negletwork * negletworkSet);

        Double[][] finalAttendenceMoney = new Double[12][5];
        for (int i = 0; i < 12; i++) {
            finalAttendenceMoney[i][0] = sumLateCome;
            finalAttendenceMoney[i][1] = sumEarlyLeave;
            finalAttendenceMoney[i][2] = sumVacate;
            finalAttendenceMoney[i][3] = sumOvertime;
            finalAttendenceMoney[i][4] = sumNegletwork;
        }
        System.out.println("finalAttendenceMoney"+finalAttendenceMoney);
            return finalAttendenceMoney;
    }

}

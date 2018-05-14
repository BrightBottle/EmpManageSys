package com.empmanagesys.module.salary.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.empmanagesys.model.Attendence;
import com.empmanagesys.model.AttendenceSet;
import com.empmanagesys.model.Insurance;
import com.empmanagesys.module.attendence.dao.AttendenceDao;
import com.empmanagesys.module.attendence.dao.AttendenceSetDao;
import com.empmanagesys.module.insurance.dao.InsuranceDao;
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
//当标于类前时, 标示类中所有方法都进行事务处理
@Transactional
public class SalaryStandardService extends BaseService{
	

    // 注入薪资信息处理Dao
    @Autowired
    private SalaryStandardDao salaryStandardDao;

    //注入考勤信息处理Dao
    @Autowired
    private AttendenceDao attendenceDao;

    //注入考勤设置信息处理Dao
    @Autowired
    private AttendenceSetDao attendenceSetDao;

    //注入保险信息处理Dao
    @Autowired
    private InsuranceDao insuranceDao;


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
     * @param name
     * @return
     */
   
	public SalaryStandard getSalaryStandard(String name) {
        SalaryStandard salaryStandard = salaryStandardDao.getSalaryStandard(name);
        return salaryStandard;
    }

    //因为最终工资计算涉及到考勤，保险，基础工资，所以注入三者的Dao层后利用其来进行数据操作，

    /**
     * 当前登陆用户实发工资计算
     * @param name
     * @return
     */
    public double finalSalaryByMyself(String name){
        List<SalaryStandard> salaryStandard = new ArrayList<>();
        SalaryStandard standard =salaryStandardDao.getSalaryStandard(name);
        salaryStandard.add(standard);

        List<Attendence> attendence = new ArrayList<>();
        Attendence attendences = attendenceDao.getAttendence(name);
        attendence.add(attendences);

        List<AttendenceSet> attendenceSet = attendenceSetDao.getAllAttendence();
        List<Insurance> insurance = insuranceDao.getAllInsurance();
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
        Double baseSalary = null;
        Integer old = null;
        Integer unemployment = null;
        Integer medical = null;
        Integer bear = null;
        Integer injury = null;
        Integer house = null;
        //考勤奖罚
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



        //五险一金
        for (Insurance insuranceInfo : insurance) {
            old = insuranceInfo.getOld();
            unemployment = insuranceInfo.getUnemployment();
            medical = insuranceInfo.getMedical();
            bear = insuranceInfo.getBear();
            injury = insuranceInfo.getInjury();
            house = insuranceInfo.getHouse();
        }

        //基础工资
        for (SalaryStandard salaryStandardInfo : salaryStandard) {
            baseSalary = salaryStandardInfo.getBaseSalary();
        }
        //最终工资
        //五险一金总和
        Double sumInsurance =(double) (old+unemployment+medical+bear+injury+house);
        //考勤罚款总和
        Double sumAttendence = (double) (sumLateCome+sumEarlyLeave+sumVacate+sumNegletwork);

        Double finalSalary = baseSalary-(sumInsurance+sumAttendence-sumOvertime);
        System.out.println(sumInsurance);
        System.out.println(sumAttendence);
        System.out.println(sumOvertime);
        System.out.println(sumInsurance+sumAttendence-sumOvertime);
        System.out.println("salaryStandard  :"+salaryStandard);
        System.out.println("attendence  :"+attendence);
        System.out.println("attendenceSet  :"+attendenceSet);
        System.out.println("insurance  :"+insurance);

        return finalSalary;
    }


    //所有的员工总工资计算

    /**
     * 所有员工工资计算
     * @return
     */
    public Double[] allFinalSalary(){
        List<SalaryStandard> salaryStandard = salaryStandardDao.getAll();
        List<Attendence> attendence = attendenceDao.getAll();
        List<AttendenceSet> attendenceSet = attendenceSetDao.getAllAttendence();
        List<Insurance> insurance = insuranceDao.getAllInsurance();

        System.out.println("salaryStandard  :"+salaryStandard);
        System.out.println("attendence  :"+attendence);
        System.out.println("attendenceSet  :"+attendenceSet);
        System.out.println("insurance  :"+insurance);

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
        Double baseSalary = null;
        Integer old = null;
        Integer unemployment = null;
        Integer medical = null;
        Integer bear = null;
        Integer injury = null;
        Integer house = null;
        int i = 0;
        //考勤奖罚

        Double[] allSumAttendenceArry = new Double[12];


        for (AttendenceSet attendenceSetInfo : attendenceSet) {
            lateComeSet = attendenceSetInfo.getLateCome();
            earlyLeaveSet = attendenceSetInfo.getEarlyLeave();
            vacateSet = attendenceSetInfo.getVacate();
            overtimeSet =attendenceSetInfo.getOvertime();
            negletworkSet = attendenceSetInfo.getNegletwork();
        }

        for (Attendence attendenceInfo : attendence) {
                i++;
                lateCome = attendenceInfo.getLateCome();
                earlyLeave = attendenceInfo.getEarlyLeave();
                vacate = attendenceInfo.getVacate();
                overtime = attendenceInfo.getOvertime();
                negletwork = attendenceInfo.getNegletwork();
                Double allSumAttendence = ((lateCome * lateComeSet) + (earlyLeave * earlyLeaveSet) + (vacate * vacateSet) + (negletwork * negletworkSet) - (overtime * overtimeSet));
                allSumAttendenceArry[i] = allSumAttendence;
            }



        //五险一金
        for (Insurance insuranceInfo : insurance) {
            old = insuranceInfo.getOld();
            unemployment = insuranceInfo.getUnemployment();
            medical = insuranceInfo.getMedical();
            bear = insuranceInfo.getBear();
            injury = insuranceInfo.getInjury();
            house = insuranceInfo.getHouse();
        }

        //基础工资
        Double[] baseSalaryArry = new Double[12];
        int i1 = 0;
        for (SalaryStandard salaryStandardInfo : salaryStandard) {
            i1++;
                baseSalaryArry[i1] = salaryStandardInfo.getBaseSalary();

        }
        //最终工资
        //五险一金总和
        Double sumInsurance =(double) (old+unemployment+medical+bear+injury+house);
        //考勤罚款总和

        Double[] finalSalary = new Double[12];
        for (int i3 = 1;i3<12;i3++){
           Double thisFinalSalary = baseSalaryArry[i3]-allSumAttendenceArry[i3]-sumInsurance;
            finalSalary[i3] = thisFinalSalary;
        }

        return finalSalary;
    }
    
}

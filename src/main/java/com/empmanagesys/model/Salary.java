package com.empmanagesys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 张罗平 on 2018/2/17.
 * 薪资表
 */
@Entity
@Table(name = "emp_salary")
public class Salary extends BaseModel{
   
	private static final long serialVersionUID = 1L;
	@Column
    private String empId;
	@Column
    private String name;
	@Column
    private String year;
	@Column
    private String month;
	@Column
    private double baseSalary;//基本工资
	@Column
    private double finalSalary;  //实发工资
	@Column
    private double lateCome;   //迟到罚款
	@Column
    private double earlyLeave;  //早退罚款
	@Column
    private double leave;      //请假罚款
	@Column
    private double overtime;   //加班奖金
	@Column
    private double negletwork;  //旷工罚款
	@Column
    private double old;           //养老保险
	@Column
    private double unemployment;  //失业保险
	@Column
    private double injury;         //工伤保险
	@Column
    private double bear;            //生育保险
	@Column
    private double medical;       //医疗保险
	@Column
    private double house;          //公积金

 
    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public double getBaseSalary() {
        return baseSalary;
    }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
    public double getFinalSalary() {
        return finalSalary;
    }
    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }
    public double getLateCome() {
        return lateCome;
    }
    public void setLateCome(double lateCome) {
        this.lateCome = lateCome;
    }
    public double getEarlyLeave() {
        return earlyLeave;
    }
    public void setEarlyLeave(double earlyLeave) {
        this.earlyLeave = earlyLeave;
    }
    public double getLeave() {
        return leave;
    }
    public void setLeave(double leave) {
        this.leave = leave;
    }
    public double getOvertime() {
        return overtime;
    }
    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }
    public double getNegletwork() {
        return negletwork;
    }
    public void setNegletwork(double negletwork) {
        this.negletwork = negletwork;
    }
    public double getOld() {
        return old;
    }
    public void setOld(double old) {
        this.old = old;
    }
    public double getUnemployment() {
        return unemployment;
    }
    public void setUnemployment(double unemployment) {
        this.unemployment = unemployment;
    }
    public double getInjury() {
        return injury;
    }
    public void setInjury(double injury) {
        this.injury = injury;
    }
    public double getBear() {
        return bear;
    }
    public void setBear(double bear) {
        this.bear = bear;
    }
    public double getMedical() {
        return medical;
    }
    public void setMedical(double medical) {
        this.medical = medical;
    }
    public double getHouse() {
        return house;
    }
    public void setHouse(double house) {
        this.house = house;
    }


}

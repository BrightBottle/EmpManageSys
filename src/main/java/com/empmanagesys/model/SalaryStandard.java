package com.empmanagesys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 张罗平 on 2018/2/17.
 * 薪资标准表
 */
@Entity
@Table(name = "emp_salarystandard")
public class SalaryStandard extends BaseModel{

	private static final long serialVersionUID = 1L;
	@Column
    private String empId;   //工号
	@Column
    private String name;
	@Column
    private double baseSalary;  //基本工资


    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SalaryStandard [empId=" + empId + ", name=" + name + ", baseSalary=" + baseSalary + "]";
	}
 
}

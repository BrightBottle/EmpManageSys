package com.empmanagesys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 张罗平 on 2018/2/17.
 * 考勤表
 */
@Entity
@Table(name = "emp_attendence")
public class Attendence extends BaseModel{
 
	private static final long serialVersionUID = 1L;
	@Column
	private String empId;     //工号
	@Column
	private String uid;     //userId
	@Column
    private String name;
	@Column
    private String year;     //入职时间 / 年
	@Column
    private String month;	  //入职时间 / 月
	@Column
    private int lateCome;    //迟到次数
	@Column
    private int earlyLeave;   //早退次数
	@Column
    private int vacate;     //请假天数
	@Column
    private int overtime;  //加班时长
	@Column
    private int negletwork; //旷工时数

    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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

    public int getLateCome() {
        return lateCome;
    }
    public void setLateCome(int lateCome) {
        this.lateCome = lateCome;
    }
    public int getEarlyLeave() {
        return earlyLeave;
    }
    public void setEarlyLeave(int earlyLeave) {
        this.earlyLeave = earlyLeave;
    }

    public int getVacate() {
		return vacate;
	}
	public void setVacate(int vacate) {
		this.vacate = vacate;
	}
	public int getOvertime() {
        return overtime;
    }
    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }
    public int getNegletwork() {
        return negletwork;
    }
    public void setNegletwork(int negletwork) {
        this.negletwork = negletwork;
    }
	@Override
	public String toString() {
		return "Attendence [empId=" + empId + ", uid=" + uid + ", name=" + name + ", year=" + year + ", month=" + month
				+ ", lateCome=" + lateCome + ", earlyLeave=" + earlyLeave + ", vacate=" + vacate + ", overtime="
				+ overtime + ", negletwork=" + negletwork + "]";
	}

    

}

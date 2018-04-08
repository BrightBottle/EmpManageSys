package com.empmanagesys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 张罗平 on 2018/2/17.
 * 考勤奖罚表
 */
@Entity
@Table(name = "emp_attendenceset")
public class AttendenceSet extends BaseModel{
 
	private static final long serialVersionUID = 1L;
	@Column
    private double lateCome;   //迟到罚款
	@Column
    private double earlyLeave;  //早退罚款
	@Column
    private double vacate;   //请假罚款
	@Column
    private double overtime;  //加班奖金
	@Column
    private double negletwork; //旷工罚款

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
   
    public double getVacate() {
		return vacate;
	}
	public void setVacate(double vacate) {
		this.vacate = vacate;
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
	@Override
	public String toString() {
		return "AttendenceSet [lateCome=" + lateCome + ", earlyLeave=" + earlyLeave + ", vacate=" + vacate
				+ ", overtime=" + overtime + ", negletwork=" + negletwork + "]";
	}

}

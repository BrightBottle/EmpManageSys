package com.empmanagesys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 张罗平 on 2018/2/17.
 * 保险表
 */
@Entity
@Table(name = "emp_insurance")
public class Insurance extends BaseModel{

	private static final long serialVersionUID = 1L;
	@Column
    private int old;  //养老保险
	@Column
    private int unemployment;  //失业保险
	@Column
    private int medical;  //医疗保险
	@Column
    private int bear;  //生育保险
	@Column
    private int injury; //工伤保险
	@Column
    private int house;  //住房公积金
    public int getOld() {
        return old;
    }
    public void setOld(int old) {
        this.old = old;
    }
    public int getUnemployment() {
        return unemployment;
    }
    public void setUnemployment(int unemployment) {
        this.unemployment = unemployment;
    }
    public int getMedical() {
        return medical;
    }
    public void setMedical(int medical) {
        this.medical = medical;
    }
    public int getBear() {
        return bear;
    }
    public void setBear(int bear) {
        this.bear = bear;
    }
    public int getInjury() {
        return injury;
    }
    public void setInjury(int injury) {
        this.injury = injury;
    }
    public int getHouse() {
        return house;
    }
    public void setHouse(int house) {
        this.house = house;
    }
	@Override
	public String toString() {
		return "Insurance [old=" + old + ", unemployment=" + unemployment + ", medical=" + medical + ", bear=" + bear
				+ ", injury=" + injury + ", house=" + house + "]";
	}

}

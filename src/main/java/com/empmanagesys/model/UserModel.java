
package com.empmanagesys.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

/**
 * 用户信息实体类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Entity
@Table(name = "emp_user")
public class UserModel extends BaseModel implements UserDetails {

    private static final long serialVersionUID = 1L;
    //工号
    @Column
	private String empId; 
    //年龄
    @Column
	private String age;
    //是否婚配
    @Column
	private String marry;
    //政治面貌
    @Column
	private String polity;
    //地址
    @Column
	private String address;
    //毕业院校
    @Column
	private String school;
    //学历
    @Column
	private String degree;  
    // 用户名称（长度：100，不允许为空）
    @Column(length = 100, nullable = false)
    private String loginName;

    // 用户名称（长度：100，不允许为空）
    @Column(length = 100, nullable = false)
    private String password;

    // 名称
    @Column(length = 100, nullable = false)
    private String name;

    // 邮箱
    @Column(length = 100, nullable = false)
    private String email;

    // 性别
    @Column(length = 100, nullable = false)
    private String gender;

    // 电话
    @Column(length = 100, nullable = false)
    private String phone;

    // 用户所属部门
    @ManyToOne(cascade = { CascadeType.PERSIST })
    private DepartmentModel department;

    // 包含角色
    @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "emp_user_role", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
    @JsonIgnore
    private Set<RoleModel> roles = Sets.newHashSet();

    // 状态
    @ManyToOne(cascade = { CascadeType.PERSIST })
    private UserStatus status;

    // 权限
    @Transient
    private GrantedAuthority[] grantedAuthority;

    public void setGrantedAuthority(GrantedAuthority[] grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

    @JsonIgnore
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(grantedAuthority);
    }

    public String getUsername() {
        return loginName;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
    
    public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMarry() {
		return marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	public String getPolity() {
		return polity;
	}

	public void setPolity(String polity) {
		this.polity = polity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public DepartmentModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }

    public boolean isDisabled() {
        //
        return this.status.equals(UserStatus.disabledStatus) ? true : false;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

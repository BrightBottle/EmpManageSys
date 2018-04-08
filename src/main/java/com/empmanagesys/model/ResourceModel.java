package com.empmanagesys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 资源实体类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Entity
@Table(name = "emp_resource")
public class ResourceModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    // 资源名称（长度：100，不允许为空）
    @Column(length = 100, nullable = false)
    private String name;

    // url
    private String url;

    // 菜单id
    @Transient
    private Long menuId;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	@Override
	public String toString() {
		return "ResourceModel [name=" + name + ", url=" + url + ", menuId=" + menuId + "]";
	}

}

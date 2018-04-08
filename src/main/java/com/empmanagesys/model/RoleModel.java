package com.empmanagesys.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

/**
 * 角色实体类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Entity
@Table(name = "emp_role")
public class RoleModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    // 角色名称（长度：100，不允许为空）
    @Column(length = 100, nullable = false)
    private String name;

    // 菜单
    @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "emp_role_menu", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "menuId", referencedColumnName = "id"))
    @Filter(name = "topMenuFilter", condition = "parent=null")
    @OrderBy("indexNo asc")
    @JsonIgnore
    private Set<MenuModel> menus = Sets.newTreeSet();

    // 资源
    @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "emp_role_resource", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "resourceId", referencedColumnName = "id"))
    @JsonIgnore
    private Set<ResourceModel> resources = Sets.newHashSet();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MenuModel> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuModel> menus) {
        this.menus = menus;
    }

    public Set<ResourceModel> getResources() {
        return resources;
    }

    public void setResources(Set<ResourceModel> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

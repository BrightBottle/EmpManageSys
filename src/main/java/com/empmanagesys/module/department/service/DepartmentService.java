package com.empmanagesys.module.department.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empmanagesys.core.BaseService;
import com.empmanagesys.core.PageContainer;
import com.empmanagesys.model.DepartmentModel;
import com.empmanagesys.model.RoleModel;
import com.empmanagesys.module.department.dao.DepartmentDao;
import com.empmanagesys.module.role.dao.RoleDao;
import com.empmanagesys.util.DateUtils;
import com.google.common.collect.Sets;

/**
 * 部门管理 Service 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Service
//当标于类前时, 标示类中所有方法都进行事物处理
@Transactional
public class DepartmentService extends BaseService{
	

    // 注入部门处理Dao
    @Autowired
    private DepartmentDao departmentDao;

    // 注入角色处理Dao
    @Autowired
    private RoleDao roleDao;

    /**
     * 获取部门分页信息
     * 
     * @return
     */
    public PageContainer getDepartmentPage(Map<String, String> params) {
        return departmentDao.getDepartmentPage(params);
    }

    /**
     * 根据Id获取部门信息
     * 
     * @return
     */
    public DepartmentModel getDepartment(Long departmentId) {
        return departmentDao.get(departmentId);
    }

    /**
     * 保存部门信息
     * 
     * @return
     */
    public void saveDepartment(DepartmentModel department) {
        // 设置最后更新时间
        department.setUpdateTime(DateUtils.timeToString(new Date()));
        departmentDao.save(department);
    }

    /**
     * 根据Id删除部门信息
     * 
     * @return
     */
    public void deleteDepartment(Long departmentId) {
        departmentDao.delete(departmentId);
    }

    /**
     * 保存部门关联的角色信息
     * 
     * @return
     */
    public void saveRelates(Long[] roleIds, Long departmentId) {
        // 获得部门实体
        DepartmentModel department = departmentDao.get(departmentId);
        // 角色set集合
        Set<RoleModel> roles = Sets.newHashSet();
        // 遍历
        for (int i = 0; i < roleIds.length; i++) {
            // 根据角色id 获得角色实体
            RoleModel role = roleDao.get(roleIds[i]);
            // 将角色实体放到角色set集合中
            roles.add(role);
        }
        // 设置部门的角色信息
        department.setRoles(roles);
        // 保存部门
        departmentDao.save(department);
    }

    /**
     * 查询所有部门信息
     * 
     * @return
     */
    public List<DepartmentModel> getAllDepartments() {
        return departmentDao.find();
    }
}

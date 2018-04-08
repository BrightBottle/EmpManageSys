
package com.empmanagesys.model;

import java.util.List;

/**
 * 菜单josn 类
 * 
 * @author 张罗平
 * @version 1.0
 */
public class MenuJSON {

    // id
    private Integer id;

    // 子菜单
    private List<MenuJSON> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MenuJSON> getChildren() {
        return children;
    }

    public void setChildren(List<MenuJSON> children) {
        this.children = children;
    }

}

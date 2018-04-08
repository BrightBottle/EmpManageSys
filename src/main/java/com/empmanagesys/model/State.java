package com.empmanagesys.model;

/**
 * 菜单树状态 类
 * 
 * @author 张罗平
 * @version 1.0
 */
public class State {

    // 是否被选中
    private boolean selected;

    public State(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}

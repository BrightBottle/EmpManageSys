package com.empmanagesys.module.index.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empmanagesys.module.user.service.UserService;
import com.empmanagesys.util.GarbledUtil;
import com.empmanagesys.web.controller.BaseController;


/**
 * 主界面处理 Controller 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController extends BaseController {

    // 注入用户处理service
    @Autowired
    private UserService userService;

    /**
     * 进入主界面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        // 传递用户拥有的菜单信息
        model.addAttribute("userMenus", userService.getCurrentUserMenus());
        // 转向（forward）前端页面，文件：/WEB-INF/views/index.jsp
        return "/index";
    }
    /**
     * 进入所有菜单导航页面
     * 
     * @param model
     * @return
     */
//    @RequestMapping(value = "/navigation/menu")
//    public String menu(Model model) {
//        // 传递用户拥有的菜单信息
//        model.addAttribute("userMenus", userService.getCurrentUserMenus());
//        // 转向（forward）前端页面，文件：/WEB-INF/views/navigation/menu.jsp
//        return "/navigation/menu";
//    }
}

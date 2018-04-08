package com.empmanagesys.module.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.empmanagesys.web.controller.BaseController;

/**
 * 首页处理 Controller 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController {

    /**
     * 进入首页
     * 
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String dashboard() {

        // 转向（forward）前端页面，文件：/WEB-INF/views/dashboard/welcome.jsp
        return "/dashboard/welcome";
    }
}

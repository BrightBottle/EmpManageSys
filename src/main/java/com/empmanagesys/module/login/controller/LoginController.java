
package com.empmanagesys.module.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empmanagesys.util.CaptchaUtil;
import com.empmanagesys.web.ResponseData;
import com.empmanagesys.web.controller.BaseController;


/**
 * 登录处理 Controller 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 进入登录页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        // 转向（forward）前端页面，文件：/WEB-INF/views/login.jsp
        return "/login";
    }

    /**
     * 生成验证码
     * 
     * @param param
     */
    @RequestMapping(value = "/captcha/generate")
    public void generateCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 生成验证码
            CaptchaUtil.generateCaptcha(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 验证码校验
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/captcha/check")
    @ResponseBody
    public ResponseData checkCaptcha(Model model, HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String code = request.getParameter("captcha");

        // 比较输入的验证码是否正确
        if (code.equalsIgnoreCase((String) request.getSession().getAttribute("login-captcha"))) {
            responseData.setStatus("0");
        } else {
            responseData.setStatus("-1");
        }
        // 返回处理结果（json 格式）
        return responseData;
    }
}

package com.empmanagesys.module.salary.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.empmanagesys.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empmanagesys.core.PageContainer;
import com.empmanagesys.module.attendence.service.AttendenceService;
import com.empmanagesys.module.insurance.service.InsuranceService;
import com.empmanagesys.module.salary.service.SalaryStandardService;
import com.empmanagesys.web.ResponseData;
import com.empmanagesys.web.controller.BaseController;

/**
 * 薪资处理 Controller 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/salary")
public class SalaryController extends BaseController {

    // 注入标准薪资处理 Service
    @Autowired
    private SalaryStandardService salaryStandardService;
    
    //注入考勤处理Service
    @Autowired
    private AttendenceService attendenceService;
  
    //注入保险处理Service
    @Autowired
    private InsuranceService insuranceService;


    /**
     * 进入标准薪资信息列表页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/salaryStandardList")
    public String SalaryStandardList(Model model) {
    	
        return "/salary/salaryStandardList";
    }

    /**
     * 进入标准薪资信息编辑页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/salaryStandardForm/{salaryStandardId}", method = RequestMethod.GET)
    public String SalaryStandardForm(Model model, @PathVariable Long salaryStandardId) {
    	SalaryStandard salaryStandard = new SalaryStandard();
        // 新增操作部门id是0L
        if (!salaryStandardId.equals(0L)) {
        	salaryStandard = salaryStandardService.getSalaryStandard(salaryStandardId);
        }
        model.addAttribute(salaryStandard);
        return "/salary/salaryStandardForm";
    }

    /**
     * 进入按条件查询标准薪资信息页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/salaryStandardInfo", method = RequestMethod.POST)
    @ResponseBody
    public PageContainer SalaryStandardInfoForm(Model model, @RequestParam Map<String, String> params) {
      
    	System.out.println(params);
    	PageContainer salaryStandard = salaryStandardService.getSalaryStandardPage(params);
    	System.out.println(salaryStandard.getData());
        return salaryStandard;
    }

    
    /**
     * 保存基础薪资信息
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveSalaryStandard")
    @ResponseBody
    public ResponseData SalaryStandardSave(Model model, @Valid @ModelAttribute("salaryStandard") SalaryStandard salaryStandard) {
        ResponseData responseData = new ResponseData();
        try {
            // 执行保存基础薪资信息
        	salaryStandardService.saveSalaryStandard(salaryStandard);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            // 基础薪资信息重复捕获重复异常
            logger.error(e.getMessage());
            responseData.setError(e.getMessage());
            responseData.setStatus("2");
        } catch (Exception e) {
            // 异常处理
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        // 返回处理结果（json 格式）
        return responseData;
    }

    /**
     * 删除标准薪资信息
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteSalaryStandard/{salaryStandardId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData SalaryStandardDelete(Model model, @PathVariable final Long salaryStandardId) {
        logger.debug("delete :salaryStandard" + salaryStandardId);
        ResponseData responseData = new ResponseData();
        try {
        	salaryStandardService.deleteSalaryStandard(salaryStandardId);
        } catch (Exception e) {
            // 异常处理
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        // 返回处理结果（json 格式）
        return responseData;
    }


    /**
     * 当前登陆用户最终工资信息
     * @return
     */
    @RequestMapping(value="salaryList")
    public String SalaryList(Model model,Principal principal){
        //员工号  年月 姓名
        model.addAttribute("attendence",attendenceService.getAttendence(principal.getName()));

        //整合考勤奖罚信息
        Double[] attendenceMoney = attendenceService.finalAttendenceMoney(principal.getName());
        model.addAttribute("sumLateCome",attendenceMoney[0]);
        model.addAttribute("sumEarlyLeave",attendenceMoney[1]);
        model.addAttribute("sumVacate",attendenceMoney[2]);
        model.addAttribute("sumOvertime",attendenceMoney[3]);
        model.addAttribute("sumNegletwork",attendenceMoney[4]);

        //保险
        model.addAttribute("insurance",insuranceService.getInsurance());

        //基础薪资
        model.addAttribute("baseSalary",salaryStandardService.getSalaryStandard(principal.getName()));

        //最终实发工资
        model.addAttribute("finalSalary",salaryStandardService.finalSalaryByMyself(principal.getName()));

        return "/salary/salaryList";
    }

    /**
     * 所有员工最终工资信息
     * @return
     */
    @RequestMapping(value="allSalary")
    public String AllsalaryList(Model model){

        System.out.println("---------------->AllsalaryList");

        //员工号  年月 姓名
        model.addAttribute("attendence",attendenceService.getAll());

        //基础薪资
        model.addAttribute("salaryStandard",salaryStandardService.getAll());
//        for (SalaryStandard baseSalary:salaryStandardService.getSalaryStandard()) {
//            model.addAttribute("baseSalary",baseSalary);
//        }

        //最终实发工资

        List<Double> list = Arrays.asList(salaryStandardService.allFinalSalary());
            model.addAttribute("finalSalary",list);



        return "/salary/allSalary";
    }


}

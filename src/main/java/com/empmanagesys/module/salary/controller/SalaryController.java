package com.empmanagesys.module.salary.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.empmanagesys.model.Attendence;
import com.empmanagesys.model.AttendenceSet;
import com.empmanagesys.model.Insurance;
import com.empmanagesys.model.SalaryStandard;
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
     * 最终工资信息
     * @return
     */
    @RequestMapping(value="salaryList")
    public String SalaryList(Model model,Principal principal){
    	
    	System.out.println(principal.getName());
    	
		List<SalaryStandard> salaryStandard = new ArrayList<>();
		SalaryStandard standard =salaryStandardService.getSalaryStandard(principal.getName());
		salaryStandard.add(standard);
		
		List<Attendence> attendence = new ArrayList<>();
		Attendence attendences = attendenceService.getAttendence(principal.getName());
		attendence.add(attendences);
		
    	List<AttendenceSet> attendenceSet = attendenceService.getSet();
    	List<Insurance> insurance = insuranceService.getInsurance();
    	Integer lateCome = null;
    	Double lateComeSet=null;
    	Integer earlyLeave = null;
    	Double earlyLeaveSet=null;
    	Integer vacate = null;
    	Double vacateSet=null;
    	Integer overtime = null;
    	Double overtimeSet=null;
    	Integer negletwork = null;
    	Double negletworkSet=null;
    	Double baseSalary = null;
    	Integer old = null;
    	Integer unemployment = null;
    	Integer medical = null;
    	Integer bear = null;
    	Integer injury = null;
    	Integer house = null;
    	//员工号  年月 姓名
    	model.addAttribute("attendence", attendence);
    	
    	//考勤奖罚
    	for (Attendence attendenceInfo : attendence) {
    		 lateCome = attendenceInfo.getLateCome();
    		 earlyLeave = attendenceInfo.getEarlyLeave();
    		 vacate = attendenceInfo.getVacate();
    		 overtime=attendenceInfo.getOvertime();
    		 negletwork= attendenceInfo.getNegletwork();
    		
    	}
		for (AttendenceSet attendenceSetInfo : attendenceSet) {
			lateComeSet = attendenceSetInfo.getLateCome();
			 earlyLeaveSet = attendenceSetInfo.getEarlyLeave();
    		 vacateSet = attendenceSetInfo.getVacate();
    		 overtimeSet =attendenceSetInfo.getOvertime();
    		 negletworkSet = attendenceSetInfo.getNegletwork();
		}
		Double sumLateCome = (lateCome*lateComeSet);
		Double sumEarlyLeave = (earlyLeave*earlyLeaveSet);
		Double sumVacate = (vacate*vacateSet);
		Double sumOvertime = (overtime*overtimeSet);
		Double sumNegletwork = (negletwork*negletworkSet);
		model.addAttribute("lateCome", sumLateCome);
		model.addAttribute("earlyLeave", sumEarlyLeave);
		model.addAttribute("vacate", sumVacate);
		model.addAttribute("overtime", sumOvertime);
		model.addAttribute("negletwork", sumNegletwork);
    	
    	
    	
    	//五险一金
		for (Insurance insuranceInfo : insurance) {
			old = insuranceInfo.getOld();
			unemployment = insuranceInfo.getUnemployment();
			medical = insuranceInfo.getMedical();
			bear = insuranceInfo.getBear();
			injury = insuranceInfo.getInjury();
			house = insuranceInfo.getHouse();
		}
		
		model.addAttribute("old", old);
		model.addAttribute("unemployment", unemployment);
		model.addAttribute("medical", medical);
		model.addAttribute("bear", bear);
		model.addAttribute("injury", injury);
		model.addAttribute("house", house);
		
    	//基础工资
    	for (SalaryStandard salaryStandardInfo : salaryStandard) {
    		baseSalary = salaryStandardInfo.getBaseSalary();
		}
    	model.addAttribute("baseSalary", baseSalary);
    	//最终工资
    	//五险一金总和
    	Double sumInsurance =(double) (old+unemployment+medical+bear+injury+house);
    	//考勤罚款总和
    	Double sumAttendence = (double) (sumLateCome+sumEarlyLeave+sumVacate+sumNegletwork);
    	
    	model.addAttribute("finalSalary", (baseSalary-(sumInsurance+sumAttendence-sumOvertime)));
    	
    	System.out.println(sumInsurance);
    	System.out.println(sumAttendence);
    	System.out.println(sumOvertime);
    	System.out.println(sumInsurance+sumAttendence-sumOvertime);
    	System.out.println("salaryStandard  :"+salaryStandard);
    	System.out.println("attendence  :"+attendence);
    	System.out.println("attendenceSet  :"+attendenceSet);
    	System.out.println("insurance  :"+insurance);
    	
		return "/salary/salaryList";
		}
    
    
    
    

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
 
}

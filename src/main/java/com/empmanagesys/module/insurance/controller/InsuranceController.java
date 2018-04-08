package com.empmanagesys.module.insurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empmanagesys.model.Insurance;
import com.empmanagesys.module.insurance.service.InsuranceService;
import com.empmanagesys.web.ResponseData;
import com.empmanagesys.web.controller.BaseController;

/**
 * 保险设置处理 Controller 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/insurance")
public class InsuranceController extends BaseController {

    // 注入考勤处理 Service
    @Autowired
    private InsuranceService insuranceService;


    /**
     * 进入保险信息设置页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/insuranceList")
    public String Insurance(Model model) {
    	List<Insurance> insurance = insuranceService.getInsurance();
    	model.addAttribute("insurance", insurance);
        return "/insurance/list";
    }
    
    /**
     * 进入保险设置信息编辑页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/form/{insuranceId}", method = RequestMethod.GET)
    public String InsuranceForm(Model model, @PathVariable Long insuranceId) {
    	Insurance insurance = new Insurance();
        
        System.out.println(insuranceId);
        // 新增操作部门id是0L
        if (!insuranceId.equals(0L)) {
        	insurance = insuranceService.getInsuranceById(insuranceId);
        }
        model.addAttribute("insurance",insurance);
        return "/insurance/form";
    }

    /**
     * 保存考勤设置信息
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public ResponseData InsuranceSave(Model model, @Valid @ModelAttribute("insurance") Insurance insurance) {
        ResponseData responseData = new ResponseData();
        try {
            // 执行保存考勤信息
        	insuranceService.saveInsurance(insurance);;
        }  catch (Exception e) {
            // 异常处理
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        // 返回处理结果（json 格式）
        return responseData;
    }

}

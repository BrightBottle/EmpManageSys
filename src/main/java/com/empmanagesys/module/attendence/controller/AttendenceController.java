package com.empmanagesys.module.attendence.controller;

import java.security.Principal;
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
import com.empmanagesys.module.attendence.service.AttendenceService;
import com.empmanagesys.web.ResponseData;
import com.empmanagesys.web.controller.BaseController;

/**
 * 考勤处理 Controller 类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/attendence")
public class AttendenceController extends BaseController {

    // 注入考勤处理 Service
    @Autowired
    private AttendenceService attendenceService;

    /**
     * 进入考勤信息列表页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/attendenceList")
    public String attendenceList(Model model) {
    	
    	System.out.println("--------->attendence");
        return "/attendence/list";
    }
    
    /**
     * 进入个人考勤信息列表页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/attendenceInfo")
    public String attendenceInfo(Model model,Principal principal) {
    	
    	model.addAttribute("attendenceInfo", attendenceService.getAttendence(principal.getName()));
    	
        return "/attendence/attendenceInfo";
    }

    /**
     * 进入考勤信息设置页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/attendenceSet")
    public String attendenceSet(Model model) {
    	List<AttendenceSet> attendenceSet = attendenceService.getSet();
    	model.addAttribute("attendenceSet", attendenceSet);
    	System.out.println("--------->attendence");
        return "/attendence/listSet";
    }
    
    /**
     * 进入考勤信息编辑页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/form/{attendenceId}", method = RequestMethod.GET)
    public String attendenceForm(Model model, @PathVariable Long attendenceId) {
        Attendence attendence = new Attendence();
        // 新增操作部门id是0L
        if (!attendenceId.equals(0L)) {
        	attendence = attendenceService.getAttendence(attendenceId);
        }
        model.addAttribute(attendence);
        return "/attendence/form";
    }

    /**
     * 进入考勤设置信息编辑页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/setForm/{attendenceSetId}", method = RequestMethod.GET)
    public String attendenceSetForm(Model model, @PathVariable Long attendenceSetId) {
        AttendenceSet attendenceSet = new AttendenceSet();
        
        System.out.println(attendenceSetId);
        // 新增操作部门id是0L
        if (!attendenceSetId.equals(0L)) {
        	attendenceSet = attendenceService.getAttendenceSet(attendenceSetId);
        }
        model.addAttribute("attendenceSet",attendenceSet);
        return "/attendence/formSet";
    }
    
    /**
     * 进入按条件查询考勤信息页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/attendenceInfoForm", method = RequestMethod.POST)
    @ResponseBody
    public PageContainer attendenceInfoForm(Model model, @RequestParam Map<String, String> params) {
      
    	System.out.println(params);
    	PageContainer attendence = attendenceService.getAttendencePage(params);
    	System.out.println(attendence);
        return attendence;
    }

    
    /**
     * 保存考勤信息
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public ResponseData attendenceSave(Model model, @Valid @ModelAttribute("attendence") Attendence attendence) {
        ResponseData responseData = new ResponseData();
        try {
            // 执行保存考勤信息
        	attendenceService.saveAttendence(attendence);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            // 考勤信息重复捕获重复异常
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
     * 保存考勤设置信息
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveSet")
    @ResponseBody
    public ResponseData attendenceSetSave(Model model, @Valid @ModelAttribute("attendenceSet") AttendenceSet attendenceSet) {
        ResponseData responseData = new ResponseData();
        try {
            // 执行保存考勤信息
        	attendenceService.saveAttendenceSet(attendenceSet);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            // 考勤信息重复捕获重复异常
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
     * 删除考勤信息
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete/{attendenceId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData attendenceDelete(Model model, @PathVariable final Long attendenceId) {
        logger.debug("delete :attendence" + attendenceId);
        ResponseData responseData = new ResponseData();
        try {
        	attendenceService.deleteAttendence(attendenceId);
        } catch (Exception e) {
            // 异常处理
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        // 返回处理结果（json 格式）
        return responseData;
    }
 
}

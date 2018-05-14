package com.empmanagesys;
import java.util.List;

import com.empmanagesys.module.salary.service.SalaryStandardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.empmanagesys.model.UserModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:hibernate.cfg.xml","classpath:applicationContext-*.xml"})
public class EmpTest {

	@Autowired
	private SalaryStandardService salaryStandardService;
	
	@Test
	public void findUsers() {
		salaryStandardService.allFinalSalary();
		System.out.println(salaryStandardService.allFinalSalary());
 	}
	
}

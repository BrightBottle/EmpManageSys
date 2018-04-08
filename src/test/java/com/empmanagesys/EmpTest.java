package com.empmanagesys;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.empmanagesys.model.UserModel;
import com.empmanagesys.module.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:hibernate.cfg.xml","classpath:applicationContext-*.xml"})
public class EmpTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void findUsers() {
		
		@SuppressWarnings("unchecked")
		List<UserModel> user = (List<UserModel>) userService.getUserPage(null);
		System.out.println(user);
 	}
	
}

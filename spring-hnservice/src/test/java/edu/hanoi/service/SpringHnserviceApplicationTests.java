package edu.hanoi.service;

import edu.hanoi.service.controller.UserRestServiceController;
import edu.hanoi.service.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@TestExecutionListeners(listeners = {ServletTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		WithSecurityContextTestExecutionListener.class})
public class SpringHnserviceApplicationTests {
	@Autowired
	UserRestServiceController userRestServiceController;

	@Test
	@WithMockUser(username = "admin", password = "654321", roles = {"USER", "ADMIN"})
	public void contextLoads() {
		List<User> users = userRestServiceController.listUsers();
		users.forEach(user -> {
			Assert.assertTrue(user.getAge() > 5);
		});
	}
}

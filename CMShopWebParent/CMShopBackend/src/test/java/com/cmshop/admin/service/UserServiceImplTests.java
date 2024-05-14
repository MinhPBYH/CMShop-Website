package com.cmshop.admin.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;

import com.cmshop.admin.repository.UserRepository;
import com.cmshop.admin.service.UserService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserServiceImplTests {
//	@Autowired
//	@Qualifier("UserServiceImpl")
//	private UserService userService;
//	
////	public UserServiceImplTests(UserService userService) {
////		this.userService = userService;
////	}
//	
//	@Test
//	public void testCheckUniqueEmail() {
////		boolean isUnique = userService.checkUniqueEmail("ta.minh@samsung.com");
////		assertThat(isUnique).isFalse();
//	}
}

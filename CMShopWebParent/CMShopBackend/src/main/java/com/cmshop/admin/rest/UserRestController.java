package com.cmshop.admin.rest;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmshop.admin.service.UserService;

@RestController
public class UserRestController {
	private UserService userService;

	public UserRestController(UserService userService) {
		super();
		this.userService = userService;
	}
	@PostMapping("/users/check_email")
	public String checkDuplicatedEmail(@Param("email") String email) {
		return userService.checkUniqueEmail(email)? "OK":"Duplicated";
	}
}

package com.cmshop.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String getViewHome() {
		return "index";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
}

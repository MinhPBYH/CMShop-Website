package com.cmshop.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/1")
	public String getViewHome() {
		return "index";
	}
}

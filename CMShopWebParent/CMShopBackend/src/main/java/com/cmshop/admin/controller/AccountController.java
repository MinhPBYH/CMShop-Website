package com.cmshop.admin.controller;

import java.io.IOException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmshop.admin.service.CMShopUserDetails;
import com.cmshop.admin.service.UserService;
import com.cmshop.admin.util.FileUploadUtil;
import com.cmshop.common.entity.User;
//
@Controller
public class AccountController {

	private UserService userService;

	public AccountController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/account")
	public String viewDetails(@AuthenticationPrincipal CMShopUserDetails loginedAccount,
			Model model) {
		String email = loginedAccount.getEmail();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		return "users/account-form";
	}
	
	@PostMapping("/account/update")
	public String saveUser(User user , RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal CMShopUserDetails loginedAccount,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhotos(fileName);
			User savedUser = userService.updateAccount(user);
			
			String uploadDir = "user-photos/" + savedUser.getId();
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {
			userService.updateAccount(user);
		}
		loginedAccount.setFirstName(user.getFirstName());
		loginedAccount.setLastName(user.getLastName());
		
		redirectAttributes.addFlashAttribute("message","Your account details have been updated.");

		return "redirect:/account";
	}
}

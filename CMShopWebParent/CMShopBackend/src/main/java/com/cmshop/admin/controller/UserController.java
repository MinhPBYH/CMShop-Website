package com.cmshop.admin.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmshop.admin.exception.UserNotFoundException;
import com.cmshop.admin.export.UserCsvExporter;
import com.cmshop.admin.export.UserExcelExporter;
import com.cmshop.admin.export.UserPdfExporter;
import com.cmshop.admin.service.UserService;
import com.cmshop.admin.service.impl.UserServiceImpl;
import com.cmshop.admin.util.FileUploadUtil;
import com.cmshop.common.entity.Role;
import com.cmshop.common.entity.User;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public String listOfFirstPage(Model model) {
		return listOfPage(1, model,"id","asc",null);
	}
	
	//@Param will map to path parameter such as "/users?name=John"
	@GetMapping("/users/page/{currentPage}")
	public String listOfPage(@PathVariable Integer currentPage, Model model,
			@RequestParam("sortField") String sortField,@RequestParam("sortOrder") String sortOrder,
			@RequestParam(value="keyword",required = false) String keyword) {
		Page<User> page = userService.listOfPage(currentPage,sortField,sortOrder,keyword);
		List<User> listUsers = page.getContent();
		long startUser = (currentPage - 1) * (userService.USER_PER_PAGE) + 1;
		long endUser = startUser + page.getNumberOfElements() - 1;
		model.addAttribute("startUser", startUser);
		model.addAttribute("endUser", endUser);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("sortOrder",sortOrder);
		model.addAttribute("sortField", sortField);
		model.addAttribute("totalUsers", page.getTotalElements());
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("keyword", keyword);
		System.out.println("key:" + keyword);
		model.addAttribute("lastPage",page.getTotalPages());
		return "users";
	}
	
	@GetMapping("/users/new")
	public String createUser(Model model) {
		User user = new User();
		user.setEnabled(true);
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("pageTitle", "Create New User");
		model.addAttribute("user",user);
		model.addAttribute("listRoles", listRoles);
		return "user-form";
	}
	
	@GetMapping("/users/edit/{userId}")
	public String updateUser(@PathVariable(name = "userId") Integer userId,Model model,
			RedirectAttributes redirectAttributes) {
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("listRoles", listRoles);
		try {
			User user = userService.findById(userId);
			System.out.println("minh" +  user.getPassword());
			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Update User (Id: " + userId + ")");
			return "user-form";
		} catch (UserNotFoundException e) {
			redirectAttributes.addAttribute("message", e.getMessage());
			return "redirect:/users";
		}
		
	}
	
	//RedirectAttributes: Used to transfer data from the current controller method to another controller method after redirecting.
	//@RequestParam: get from form input that have name "image"
	@PostMapping("/users/save")
	public String saveUser(User user , RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhotos(fileName);
			User savedUser = userService.save(user);
			
			String uploadDir = "user-photos/" + savedUser.getId();
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {
			userService.save(user);
		}
		
		boolean isUpdateUser = (user.getId() != null);
		if(isUpdateUser) {
			redirectAttributes.addFlashAttribute("message","The user(id: "+ user.getId() + ") have bean updated successfully.");
			return "redirect:/users/page/1?sortField=id&sortOrder=asc&keyword="+user.getEmail();
			
		}else {
			redirectAttributes.addFlashAttribute("message","The user have bean created successfully.");
		}

		return "redirect:/users";
	}
	
	@GetMapping("/users/delete/{userId}")
	public String deleteUser(@PathVariable Integer userId,
			RedirectAttributes redirectAttributes) {
		try {
			userService.deleteById(userId);
			redirectAttributes.addFlashAttribute("message","The user(id: "+ userId + ") have bean deleted successfully.");

		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/users";
	}
	
	@GetMapping("/users/enable/{userId}/{status}")
	public String updateEnableStatus(@PathVariable("userId") Integer userId, @PathVariable("status") boolean enable,
			RedirectAttributes redirectAttributes,Model model,
			@RequestParam("sortField") String sortField,@RequestParam("sortOrder") String sortOrder,
			@RequestParam("currentPage") String currentPage,
			@RequestParam(value = "keyword",required = false) String keyword) {
		try {
			userService.updateEnableStatus(userId, enable);
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		if(keyword != null){
			return"redirect:/users/page/" + currentPage  + "?sortField=" + sortField +"&sortOrder=" + sortOrder + "&keyword=" + keyword;
		}
		return"redirect:/users/page/" + currentPage  + "?sortField=" + sortField +"&sortOrder=" + sortOrder;
	}
	
	@GetMapping("/users/export/csv")
	public void exportToCsv(HttpServletResponse response) throws IOException {
		List<User> listUsers = userService.findAll();
		UserCsvExporter exporter = new UserCsvExporter();
		exporter.export(listUsers, response);
	}
	
	@GetMapping("/users/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<User> listUsers = userService.findAll();
		UserExcelExporter exporter = new UserExcelExporter();
		exporter.export(listUsers, response);
	}
	
	@GetMapping("/users/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<User> listUsers = userService.findAll();
		UserPdfExporter exporter = new UserPdfExporter();
		exporter.export(listUsers, response);
	}
}

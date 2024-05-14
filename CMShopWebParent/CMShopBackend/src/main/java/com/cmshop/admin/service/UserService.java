package com.cmshop.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cmshop.admin.exception.UserNotFoundException;
import com.cmshop.common.entity.Role;
import com.cmshop.common.entity.User;

public interface UserService {
	int USER_PER_PAGE = 3;
	
	List<User> findAll();
	List<Role> listRoles();
	User save(User user);
	boolean checkUniqueEmail(String email);
	User findById(Integer id) throws UserNotFoundException;
	void deleteById(Integer id) throws UserNotFoundException;
	void updateEnableStatus(Integer id, boolean enable) throws UserNotFoundException;
	Page<User> listOfPage(Integer pageNum, String sortField, String sortOrder, String keyword);
	
}

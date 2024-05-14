package com.cmshop.admin.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmshop.admin.exception.UserNotFoundException;
import com.cmshop.admin.repository.RoleRepository;
import com.cmshop.admin.repository.UserRepository;
import com.cmshop.admin.service.UserService;
import com.cmshop.common.entity.Role;
import com.cmshop.common.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;

	private RoleRepository roleRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}
	@Override
	public List<Role> listRoles(){
		return roleRepository.findAll();
	}
	@Override
	public User save(User user) {
		boolean isUpdateUser = (user.getId() != null);
		
		if(isUpdateUser) {
			User savedUser = userRepository.findById(user.getId()).get();
			
			if(user.getPassword().isEmpty()) {
				user.setPassword(savedUser.getPassword());
			}else {
				encodePassword(user);
			}
			
			if(user.getPhotos().isEmpty()) {
				user.setPhotos(null);
			}
		}else {
			encodePassword(user);
		}
		
		return userRepository.save(user);	
	}
	
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
	@Override
	public boolean checkUniqueEmail(String email) {
		User user = userRepository.findByEmail(email);
		
		return user == null;
	}
	@Override
	public User findById(Integer id) throws UserNotFoundException {
		Optional<User> optional = userRepository.findById(id);
		try {
			return optional.get();
		}catch (NoSuchElementException e) {
			throw new UserNotFoundException("Cound not found user with Id " + id + "!!!");
		}
	}
	@Override
	public void deleteById(Integer id) throws UserNotFoundException {
		Long countUser = userRepository.countById(id);
		if(countUser == null || countUser == 0)
			throw new UserNotFoundException("Cound not found user with Id " + id + "!!!");
		
		userRepository.deleteById(id);
	}
	@Override
	public void updateEnableStatus(Integer id, boolean enable) throws UserNotFoundException {
		Long countUser = userRepository.countById(id);
		if(countUser == null || countUser == 0)
			throw new UserNotFoundException("Cound not found user with Id " + id + "!!!");
		userRepository.updateEnableStatus(id, enable);
	}
	// sortDir: sort direction
	@Override
	public Page<User> listOfPage(Integer pageNum, String sortField, String sortOrder, String keyword) {
		Sort sort = Sort.by(sortField);
		
		sort = sortOrder.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, USER_PER_PAGE, sort);
		
		if(keyword != null)
			return userRepository.findAll(keyword, pageable);
		return userRepository.findAll(pageable);
	}
	
	
}

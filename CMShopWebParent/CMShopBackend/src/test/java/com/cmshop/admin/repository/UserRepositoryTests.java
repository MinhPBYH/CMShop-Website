package com.cmshop.admin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.cmshop.common.entity.Role;
import com.cmshop.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	RoleRepository roleRepository;
//	
//	@Test
//	public void testCreateNewUserWithOneRole() {
//		Role roleAdmin = roleRepository.findById(1).get();
//		
//		User userCongMinh = new User("ta.minh@samsung.com", "Minh", "Cong", "minh1234");
//		userCongMinh.addRole(roleAdmin);
//		
//		User savedUser = userRepository.save(userCongMinh);
//		assertThat(savedUser.getId()).isGreaterThan(0);
//	}
//	
//	@Test
//	public void testCreateNewUserWithTwoRoles() {
//		Role roleSalesperson = roleRepository.findById(2).get();
//		Role roleEditor = roleRepository.findById(3).get();
//		
//		User userCaoHang = new User("hang@mail.com", "Hang", "Cao", "hang1234");
//		userCaoHang.addRole(roleSalesperson);
//		userCaoHang.addRole(roleEditor);
//		
//		User savedUser = userRepository.save(userCaoHang);
//		assertThat(savedUser.getId()).isGreaterThan(0);
//
//	}
//	
//	@Test
//	public void testUpdateUserDetail() {
//		User userMinh = userRepository.findById(1).get();
//		userMinh.setEnabled(true);
//		userRepository.save(userMinh);
//	}
//	
//	@Test
//	public void testGetUserById() {
//		User userMinh = userRepository.findById(1).get();
//		assertThat(userMinh.getFirstName()).isEqualTo("Minh");
//		assertThat(userMinh.getLastName()).isEqualTo("Cong");
//	}
//	
//	@Test
//	public void testFindByNotExistedEmail() {
//		User user = userRepository.findByEmail("asf@fsfdfas");
//		assertThat(user).isNull();
//	}
//	
//	@Test
//	public void testFindByExistedEmail() {
//		User user = userRepository.findByEmail("ta.minh@samsung.com");
//		assertThat(user).isNotNull();
//	}
//	
//	@Test
//	public void testCountById() {
//		Integer id = 1200;
//		Long countById = userRepository.countById(id);
//		
//		System.out.println("Count:" + countById);
//	}
//	
//	@Test
//	public void testCountByEmail() {
//		String email = "ta.minh@samsung.com1";
//		Long countById = userRepository.countByEmail(email);
//		
//		System.out.println("Count:" + countById);
//	}
//	
//	@Test
//	public void testUpdateEnableStatus() {
//		userRepository.updateEnableStatus(8, false);
//	}
//	
//	@Test
//	public void testListFirstPage() {
//		int pageNumber = 0;
//		int pageSize = 4;
//		//Pageable determines the number of records in page with index 0 
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		
//		// Page object contains records of current page
//		Page<User> page = userRepository.findAll(pageable);
//		
//		page.getContent().forEach(System.out::println);
//	}
//	
//	@Test
//	public void testSerchByFindAll() {
//		int pageNumber = 0;
//		int pageSize = 25;
//		//Pageable determines the number of records in page with index 0 
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		
//		// Page object contains records of current page
//		
//		userRepository.findAll("mail",pageable).getContent().forEach(System.out::println);
//	}
}

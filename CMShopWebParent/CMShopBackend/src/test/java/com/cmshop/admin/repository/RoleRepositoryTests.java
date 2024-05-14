package com.cmshop.admin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cmshop.admin.repository.RoleRepository;
import com.cmshop.common.entity.Role;

//@DataJpaTest will run the test against an auto created database(by spring).
@DataJpaTest
//replace = NONE. So it will run the test against the real database
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	@Autowired
	private RoleRepository roleRepository;
	
	//first role
//	@Test
//	public void testCreateFirstRole() {
//		Role roleAdmin = new Role("Admin", "Manage everything");
//		Role savedRole = roleRepository.save(roleAdmin);
//		
//		assertThat(savedRole.getId()).isGreaterThan(0);
//	}
//	
//	//rest roles
//	@Test
//	public void testCreateRestRoles() {
//		Role roleSalesperson = new Role("Salesperson", "Manage product price, customers, "
//				+ "shipping, orders and sales report");
//		
//		Role roleEditor = new Role("Editor", "Manage categories, brands, products, articles "
//				+ "and menus");
//		
//		Role roleShipper = new Role("Shipper", "View products, view orders and update order status");
//		
//		Role roleAssistant = new Role("Assistant", "Manage questions and reviews");
//		int numRolesPrev = roleRepository.findAll().size();
//		roleRepository.save(roleSalesperson);
//		int numRolesNext = roleRepository.findAll().size();
//		assertThat(numRolesNext - numRolesPrev).isEqualTo(1);
//		roleRepository.save(roleEditor);
//		numRolesNext = roleRepository.findAll().size();
//		assertThat(numRolesNext - numRolesPrev).isEqualTo(2);
//		roleRepository.save(roleShipper);
//		numRolesNext = roleRepository.findAll().size();
//		assertThat(numRolesNext - numRolesPrev).isEqualTo(3);
//		roleRepository.save(roleAssistant);
//		numRolesNext = roleRepository.findAll().size();
//		assertThat(numRolesNext - numRolesPrev).isEqualTo(4);
//		
//	}
}

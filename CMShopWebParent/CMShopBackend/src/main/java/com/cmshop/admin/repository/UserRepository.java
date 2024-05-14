package com.cmshop.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cmshop.common.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User findByEmail(@Param("email") String email);
	
	public Long countById(Integer id);
	
	public Long countByEmail(String email);
	
	@Query("UPDATE User u SET u.enabled =?2 WHERE u.id = ?1")
	@Modifying
	@Transactional
	public void updateEnableStatus(Integer id, boolean enabled);
	
	@Query("SELECT u FROM User u WHERE CONCAT(u.id, ' ',u.email,' ',u.firstName, ' ', u.lastName) LIKE %?1%"
			+ "OR CONCAT(u.lastName, ' ', u.firstName) LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
}

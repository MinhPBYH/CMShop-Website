package com.cmshop.admin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cmshop.common.entity.Role;
import com.cmshop.common.entity.User;

//this class represents for details of user information and is used by Spring security.
public class CMShopUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	
	public CMShopUserDetails(User user) {
		this.user = user;
	}
	
	// return a list of assigned roles of user by using list authorities
	// spring will use this function to get list of assigned roles
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	// return true, meaning that this user is not expired
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public String getFullname() {
		return this.user.getFirstName() + " " + this.user.getLastName();
	}
	
	public String getEmail() {
		return this.user.getEmail();
	}
	
	public void setFirstName(String firstName) {
		this.user.setFirstName(firstName);
	}
	
	public void setLastName(String lastName) {
		this.user.setLastName(lastName);
	}
	
	public String getRoles() {
		return this.user.getRoles().toString();
	}
}

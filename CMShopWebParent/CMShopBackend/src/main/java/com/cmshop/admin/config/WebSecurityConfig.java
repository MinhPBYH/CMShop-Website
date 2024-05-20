package com.cmshop.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cmshop.admin.service.CMShopUserDetailsService;

@Configuration
public class WebSecurityConfig {

	@Bean
	UserDetailsService userDetailsService() {
		return new CMShopUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	//config security filter
	//SecurityFilterChain: is represented to config security filter chain
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider());

		http.authorizeHttpRequests(auth -> auth
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/login")
						.usernameParameter("email")
						.permitAll())
				.logout(logout -> logout.permitAll())
				.rememberMe(rem -> rem.key("eMrebmemeRpohSMC_00028032")
						.tokenValiditySeconds(7*24*60*60))
		;

		return http.build();
	}

	// config URL such as /images/**","js/**","webjars/**","css/** when using thymeleaf,
	// ensuring that they do not go through Spring Security filters
	@Bean
	WebSecurityCustomizer configureWebSecurity() {
		return web -> web.ignoring().requestMatchers("/images/**","js/**","webjars/**","css/**","icon/**");
	}

}

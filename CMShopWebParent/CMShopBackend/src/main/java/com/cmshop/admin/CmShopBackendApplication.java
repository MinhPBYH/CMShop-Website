package com.cmshop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EntityScan({"com.cmshop.common.entity","com.cmshop.admin"})
public class CmShopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmShopBackendApplication.class, args);
	}

}

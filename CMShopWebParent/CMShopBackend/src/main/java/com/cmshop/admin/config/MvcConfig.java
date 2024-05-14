package com.cmshop.admin.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//config class to expose image for the client
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	//this method adds ResourceHandlerRegistry, helping application to know where to find static resource like image, css,...
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirName = "user-photos";
		//A Path object represents path to named directory "user-photos"
		Path userPhotoDir = Paths.get(dirName);
		
		//get absolute path to String type
		String userPhotoPath = userPhotoDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/" + dirName + "/**")
				.addResourceLocations("file:/" + userPhotoPath + "/");
	}

}

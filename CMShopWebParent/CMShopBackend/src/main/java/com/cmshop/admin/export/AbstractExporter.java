package com.cmshop.admin.export;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cmshop.common.entity.User;

import jakarta.servlet.http.HttpServletResponse;

public abstract class AbstractExporter {

	public void setResponseHeader(HttpServletResponse response, String contentType, String fileType) {
		DateFormat dateFormater = new SimpleDateFormat("HH:mm:ss_MM-dd-yyyy");
		String timestamp = dateFormater.format(new Date());
		String fileName = "cmshop_users_" + timestamp +"." + fileType;
		System.out.println("time:"+timestamp);
		response.setContentType(contentType);
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headerValue);
	}
	
	public abstract void export(List<User> listUsers, HttpServletResponse response) throws IOException;
}

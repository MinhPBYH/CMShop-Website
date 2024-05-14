package com.cmshop.admin.export;

import java.io.IOException;
import java.util.List;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.cmshop.common.entity.User;

import jakarta.servlet.http.HttpServletResponse;

public class UserCsvExporter extends AbstractExporter{
	@Override
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		setResponseHeader(response, "text/csv", "csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"User ID","Email","First Name", "Last Name","Roles","Enabled"};
		String[] fieldMapping = {"id","email","firstName","lastName","roles","enabled"};
		csvWriter.writeHeader(csvHeader);
		
		for (User user: listUsers) {
			csvWriter.write(user,fieldMapping);
		}
		
		csvWriter.close();
	}
}

package com.cmshop.admin.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.cmshop.common.entity.User;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class UserPdfExporter extends AbstractExporter {

	@Override
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		setResponseHeader(response, "application/pdf", "pdf");

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
		font.setSize(16);
		font.setColor(Color.CYAN);
		
		document.open();
		Paragraph paragraphHeader = new Paragraph("List of users",font);
		paragraphHeader.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraphHeader);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(15);
		
		writeTableHeader(table);
		writeTableData(table, listUsers);
		
		document.add(table);
		document.close();
	}
	
	private void writeTableData(PdfPTable table, List<User> listUsers) {
		for (User user : listUsers) {
			table.addCell(String.valueOf(user.getId()));
			table.addCell(user.getEmail());
			table.addCell(user.getFirstName());
			table.addCell(user.getLastName());
			table.addCell(user.getRoles().toString());
			table.addCell(String.valueOf(user.isEnabled()));
		}
	}
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.cyan);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(13);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("User Id", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("First Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Last Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Roles", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Enabled", font));
		table.addCell(cell);
		
	}

}

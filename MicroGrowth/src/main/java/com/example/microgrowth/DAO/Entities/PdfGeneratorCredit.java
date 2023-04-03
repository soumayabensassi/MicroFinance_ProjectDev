package com.example.microgrowth.DAO.Entities;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PdfGeneratorCredit {
    public void generate(List<Credit> creditList, HttpServletResponse response) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        // Creating header with logo
        Image logo = Image.getInstance("logo_MicroGrowth.png"); // Replace with path to your logo image
        logo.scaleToFit(100, 60); // Resize image to fit within 100x100 pixels
        Chunk logoChunk = new Chunk(logo, 0, -20, false);
        Phrase logoPhrase = new Phrase(logoChunk);

        HeaderFooter header = new HeaderFooter(logoPhrase, false);
        document.setHeader(header);
        // Opening the created document to change it
        document.open();
        // Creating font
        // Setting font style and size
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(24);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("List of Credits", fontTitle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        // Adding spacing between the title and table
        document.add(new Paragraph("\n"));
        // Creating a table of the 5 columns
        PdfPTable table = new PdfPTable(5);
        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1f, 1f, 1.5f, 1f, 1f});
        table.setSpacingBefore(10);
        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();
        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.GRAY);
        cell.setBorderColor(CMYKColor.WHITE);
        cell.setPadding(8);
        // Creating font
        // Setting font style and size
        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontHeader.setColor(CMYKColor.WHITE);
        fontHeader.setSize(14);
        // Adding headings in the created table cell or header
        cell.setPhrase(new Phrase("Last Name", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Phrase("First Name", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Amount Loan", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Interest Rate", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Duration", fontHeader));
        table.addCell(cell);
        // Iterating the list of credits
        for (Credit credit : creditList) {
            // Adding credit last name
            PdfPCell lastNameCell = new PdfPCell(new Phrase(credit.getUsers().getLasttName(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            lastNameCell.setBorderColor(CMYKColor.WHITE);
            lastNameCell.setPadding(8);
            table.addCell(lastNameCell);
            // Adding user first name
            PdfPCell firstNameCell = new PdfPCell(new Phrase(credit.getUsers().getFirstName(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            firstNameCell.setBorderColor(CMYKColor.WHITE);
            firstNameCell.setPadding(8);
            table.addCell(firstNameCell);
            // Adding amount credit
            PdfPCell amountCreditCell = new PdfPCell(new Phrase(String.valueOf(credit.getAmount_credit()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            amountCreditCell.setBorderColor(CMYKColor.WHITE);
            amountCreditCell.setPadding(8);
            table.addCell(amountCreditCell);
            // Adding user first name
            PdfPCell InterestRateCell = new PdfPCell(new Phrase(String.valueOf(credit.getIntrestRaiting()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            InterestRateCell.setBorderColor(CMYKColor.WHITE);
            InterestRateCell.setPadding(8);
            table.addCell(InterestRateCell);
            // Adding user first name
            PdfPCell DureeCell = new PdfPCell(new Phrase(String.valueOf(credit.getDuree()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            DureeCell.setBorderColor(CMYKColor.WHITE);
            DureeCell.setPadding(8);
            table.addCell(DureeCell);
        }
        // Adding the created table to the document
        document.add(table);
        // Adding footer with page number and date
        Paragraph footer = new Paragraph("Page " + writer.getPageNumber() + " | " + new Date().toString(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL));
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
        // Closing the document
        document.close();
    }
}

package com.example.microgrowth.DAO.Util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

import com.example.microgrowth.DAO.Entities.Transaction;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;



public class TransactionPDFExporter {
    private List<Transaction> listTransactions;

    public TransactionPDFExporter(List<Transaction> listTransactions) {
        this.listTransactions = listTransactions;
    }



    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("DATE", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("TYPE", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DESCRIPTION", font));
        table.addCell(cell);



        cell.setPhrase(new Phrase("AMOUNT", font));
        table.addCell(cell);



    }

    private void writeTableData(PdfPTable table) {

        for (Transaction transaction : listTransactions) {
            table.addCell((String.valueOf(transaction.getDateTransaction())));
            table.addCell(String.valueOf(transaction.getTypeTransaction()));
            table.addCell(String.valueOf(transaction.getRibSource()));
            table.addCell(String.valueOf(transaction.getAmountTransaction()));

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Account Statement", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}

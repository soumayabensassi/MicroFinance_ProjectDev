package com.example.microgrowth.Service.Classe;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.MethodInvestissement;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class BonDeCommandeService {
    @Autowired
    private List<Investment> listInvestments ;

    private IInvestment iInvestment;

    public BonDeCommandeService(List<Investment> listInvestments) {
        this.listInvestments = listInvestments;


    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new BaseColor(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue()));

        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(new BaseColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue()));

        cell.setPhrase(new Paragraph("ID d'investissement ", font));
        table.addCell(cell);

        cell.setPhrase(new Paragraph("MethodInvestissement", font));
        table.addCell(cell);

        cell.setPhrase(new Paragraph("AmountInves", font));
        table.addCell(cell);

        cell.setPhrase(new Paragraph("Duree", font));
        table.addCell(cell);

        cell.setPhrase(new Paragraph("Date", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        MethodInvestissement methodInvestissement;

        for (Investment inv : listInvestments) {
            table.addCell(String.valueOf(inv.getIdInvestment()));
            table.addCell(String.valueOf(inv.getMethodInvestissement()));
            table.addCell(String.valueOf(inv.getAmountInves()));
            table.addCell(String.valueOf(inv.getDuree()));
            table.addCell(String.valueOf(inv.getDateInv()));

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        BaseColor color = new BaseColor(Color.BLACK.getRGB());
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, color);

        Paragraph p = new Paragraph("Investissements", font);

        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}

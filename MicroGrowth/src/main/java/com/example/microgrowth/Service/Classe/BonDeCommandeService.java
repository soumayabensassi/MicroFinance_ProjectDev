package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class BonDeCommandeService {

    @Autowired
    private IInvestment iInvestment;

    public byte[] genererBonDeCommande(User utilisateur, Investment investment) {

        // Initialisation du document
        Document document = new Document(PageSize.A4);

        // Initialisation du flux de sortie
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // Création du writer PDF
            PdfWriter.getInstance(document, outputStream);

            // Ouverture du document
            document.open();

            // Ajout des informations de l'utilisateur
            document.add(new Paragraph("Nom: " + utilisateur.getFirstName()));
            document.add(new Paragraph("Prénom: " + utilisateur.getLasttName()));
            document.add(new Paragraph("Adresse: " + utilisateur.getPhone()));
            document.add(new Paragraph("Email: " + utilisateur.getEmail()));
            document.add(new Paragraph("Cin: " + utilisateur.getCin()));
            document.add(new Paragraph("téléphone"+utilisateur.getPhone()));
            document.add(new Paragraph("Profession"+utilisateur.getProfession()));

            double taux = iInvestment.calculerTauxInteret(investment.getMethodInvestissement(), investment.getAmountInves(), investment.getDuree());
            double interet = iInvestment.calculerInteret(investment.getMethodInvestissement(), investment.getAmountInves(), investment.getDuree());

            // Ajout des informations de l'investissement
            document.add(new Paragraph("identifiant: " + investment.getIdInvestment()));
            document.add(new Paragraph("Montant investi: " + investment.getAmountInves() + " €"));
            document.add(new Paragraph("Intérêt: " + interet + " €"));
            document.add(new Paragraph("Taux: " + taux + " %"));

            // Fermeture du document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retour du flux de sortie sous forme de tableau de bytes
        return outputStream.toByteArray();
    }
}

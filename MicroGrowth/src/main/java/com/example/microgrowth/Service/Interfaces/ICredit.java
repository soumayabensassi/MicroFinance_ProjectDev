package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Penalite;
import com.example.microgrowth.DAO.Entities.User;
import com.lowagie.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ICredit {
    
    String add_credit_user(Credit c);

    Credit add_credit_admin(Credit c);

    Credit edit(Credit c);

    List<Credit> selectAll();

    Credit SelectById(int id_credit);

    void deleteById(int id_credit);
    List<Credit> SelectByEmail(String email);
     int scoreCredit(int id) ;
     //int afficherScore(User user, Credit credit, BankAcount bankAcount);
    float calcul_taux(float montant,int duree);
    void calcul_tableau_credit(Credit c);
    double calcul_Rentabilite_parCredit(Credit c);
    double calcul_Rentabilite_parCreditNonActialise(Credit c);
    double calcul_Net_Interest_Marge();
    double TauxRemboursement();
    double TauxDeDefaut();
    double CalculActifCredit();
    double CalculActifRéserve();
    double CalculResultatNET();

    void ajouter_date_30(Credit c);
    void Accorde_penalite();
    void annuler_penalite();
    void AffecterCreditAuUser(Credit credit);
    void RefuserCreditAuUser(Credit credit);

     float calculateInterestRate(Credit c) ;
     void SimulateurCredit(float MontantCredit,int nbmois);
    File genererCreditPDF(int nbmois) throws IOException, DocumentException;
    void envoyerCreditParEmail() throws javax.mail.MessagingException;
    double MaxCredit(int nbmois);




}

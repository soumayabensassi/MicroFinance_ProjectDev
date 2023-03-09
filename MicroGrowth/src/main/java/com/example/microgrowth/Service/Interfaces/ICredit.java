package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.User;

import java.util.List;

public interface ICredit {
    
    Credit add_credit_user(Credit c);

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
}

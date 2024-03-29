package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.MethodInvestissement;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.InvestmentRepository;
import com.example.microgrowth.Service.Interfaces.IInvestment;

import com.example.microgrowth.Service.Interfaces.IMicroGrowth;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;


@Service
@AllArgsConstructor
public class InvestmentService implements IInvestment {
    @Autowired
    private  EmailService EmailService;

    private JavaMailSender JavaMailSender;
    InvestmentRepository investmentRepository;



    @Override

    public Investment add(Investment inv) {

        return  investmentRepository.save(inv);
    }

    @Override
    public List<Investment> selectAll() {
        return investmentRepository.findAll();
    }
   /* @Override
    public List<Investment> selectByiduse(int idInvestment) {
        return investmentRepository.getlistByidUser(idInvestment);
    }*/

    @Override
    public Investment selectById(int idInvestment) {
        return investmentRepository.findById(idInvestment).orElse(null);
    }

    /*@Override
    public List<Investment> selectByUserID() {
        return investmentRepository.findAllById(in);
    }*/

    @Override
    public void deleteById(int idInvestment) {
        investmentRepository.deleteById(idInvestment);
    }


    @Override
    public Investment modif(Investment inv) {
        return  investmentRepository.save(inv);
    }


//////****************calculer le taux d'interet***************//////////////

    @Override
    public double calculerTauxInteret(MethodInvestissement methodInvestissement, double amountInves, int duree) {
        double tmm = obtenirTauxInteretTMM();
        double tauxBase = tmm + 0.1;
        double tauxSupplementaire = 0.0;
        if (methodInvestissement == MethodInvestissement.PLACEMENT_PRECOMPTE) {
            if (amountInves >= 5000 && duree >= 90 && duree <= 180 ) { tauxSupplementaire = 0.5;}
            
            else if (amountInves >= 1000 && amountInves <= 5000 && duree >= 90) { tauxSupplementaire = 0.3;}
            else if (amountInves < 1000 && duree - 90 >= 0) { tauxSupplementaire = 0.1;}
        }
        if (methodInvestissement == MethodInvestissement.PLACEMENT_POSCOMPTE) {
            if (amountInves >= 5000 && duree >= 90 ) { tauxSupplementaire = 0.7;}
            else if (amountInves >= 1000 && amountInves <= 5000 && duree >= 90) { tauxSupplementaire = 0.5;}
            else if (amountInves < 1000 && duree - 90 >= 0) { tauxSupplementaire = 0.2;}
        }
        double tauxInteret = tauxBase + tauxSupplementaire;
        return tauxInteret;
    }



    //////****************calculer l'interet***************//////////////
    @Override
    public double calculerInteret(MethodInvestissement methodInvestissement, double amountInves, int duree) {
        double tauxInteret = this.calculerTauxInteret(methodInvestissement, amountInves, duree);
        double amm = amountInves * tauxInteret;
        double durartion = (double) duree;
        double amdure = amm * durartion;
        double tdure = tauxInteret * durartion;
        double divpres = tdure + 36500.0;
        double Interet = 0.0;
        if (methodInvestissement == MethodInvestissement.PLACEMENT_POSCOMPTE) {
            Interet = amdure / 36500.0;
        }
        if (methodInvestissement == MethodInvestissement.PLACEMENT_PRECOMPTE) {
            Interet = amdure / divpres;
        }
        return Interet;
    }




    private double obtenirTauxInteretTMM() {
        return Double.parseDouble("8.02");
    }
//////////////////////suivi investissement///////////////
//////

///////////Revenu des placement(PLACEMENT_PRECOMPTE) <<soumaya>>//////////////////////////
    public double Revenu_INVISTISSEMENT(Investment investment)
    {//FV = PV * (1 + r)^n
       double tauxGagner=Math.pow(1.30,investment.getDuree());
       double taux=1+((this.calculerTauxInteret(investment.getMethodInvestissement(),investment.getAmountInves(),investment.getDuree())))/100;
        System.out.println(taux);
       double tauxDonnerAuInvesstiseur=Math.pow(taux,investment.getDuree());
       double MontantGagner=investment.getAmountInves()*tauxGagner;
       double montantDonnerAuInvesstiseur=investment.getAmountInves()*tauxDonnerAuInvesstiseur;
   return MontantGagner-montantDonnerAuInvesstiseur;
    }

    @Override
    public List<Investment> getInvestmentByAnnee() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int year = calendar.get(Calendar.YEAR);
        List<Investment> list=investmentRepository.creditParAnnee(year);
        return list;
    }


    ///////////////////PONZII////////
    IMicroGrowth iMicroGrowth ;
    public List<String> calculerTauxPonzii(int iduser) {
        List<User> users = investmentRepository.getNbrINPONZI();
        int n = users.size();
        double sommef = 0.0;
        double sommeTotale=0.0;
        List<String> ponzi = new ArrayList<>();
        System.out.println(n);
        for (User inve : users) {
            if (inve.getIdUser() == iduser) {
                double amount = investmentRepository.getAmountbyuserID(iduser);
                for (int i = 0; i <= (n*n); i++) {
                    sommef = amount * 3 + (amount / n) * i;
                    ponzi.add(Double.toString(sommef));
                    System.out.println(i);
                    System.out.println(sommef);
                    sommeTotale += sommef;
                }
                ponzi.add(Double.toString(sommeTotale));
            }
        }
        return ponzi;
    }

    public double session (double n, double x) {
        return Math.log(x) / Math.log(n);
    }


}










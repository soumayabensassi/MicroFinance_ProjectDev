package com.example.microgrowth.Service.Classe;
import java.math.*;
import java.time.*;
import java.util.Currency;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.MethodInvestissement;
import com.example.microgrowth.DAO.Repositories.InvestmentRepository;
import com.example.microgrowth.Service.Interfaces.IInvestment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.*;




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

    @Override
    public Investment selectById(int idInvestment) {
        return investmentRepository.findById(idInvestment).orElse(null);
    }

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
    public BigDecimal calculerTauxInteret(MethodInvestissement MethodInvestissement,BigDecimal amountInves,int duree) {
        BigDecimal tmm = obtenirTauxInteretTMM();
        BigDecimal tauxBase = tmm.add(new BigDecimal("0.1"));
        BigDecimal tauxSupplementaire = BigDecimal.ZERO;
        if (MethodInvestissement == com.example.microgrowth.DAO.Entities.MethodInvestissement.PLACEMENT_PRECOMPTE) {

            if  (amountInves.compareTo(new BigDecimal("10000")) >= 0 && (amountInves.compareTo(new BigDecimal("5000")) <= 0 && (duree-365) >= 0)){
                tauxSupplementaire = new BigDecimal("0.5");
            } else if (amountInves.compareTo(new BigDecimal("5000")) >= 0 && (amountInves.compareTo(new BigDecimal("1000")) <= 0 && (duree-180) >= 0))  {
                tauxSupplementaire = new BigDecimal("0.3");
            } else if (amountInves.compareTo(new BigDecimal("1000")) >= 0  && (duree-90) >= 0) {
                tauxSupplementaire = new BigDecimal("0.1");
            }
        }
        if (MethodInvestissement == com.example.microgrowth.DAO.Entities.MethodInvestissement.PLACEMENT_POSCOMPTE) {

            if (amountInves.compareTo(new BigDecimal("10000")) >= 0 && (duree-365) >= 0) {
                tauxSupplementaire = new BigDecimal("0.7");
            } else if (amountInves.compareTo(new BigDecimal("5000")) >= 0 && (duree-180) >= 0) {
                tauxSupplementaire = new BigDecimal("0.4");
            } else if (amountInves.compareTo(new BigDecimal("1000")) >= 0 && (duree-90) >= 0) {
                tauxSupplementaire = new BigDecimal("0.2");
            }
        }
        BigDecimal tauxInteret = tauxBase.add(tauxSupplementaire);
        return tauxInteret;
    }
    //////****************calculer l'interet***************//////////////
    @Override
    public BigDecimal calculerInteret(MethodInvestissement MethodInvestissement, BigDecimal amountInves, int duree) {
        BigDecimal Interet = BigDecimal.ZERO;
        BigDecimal tauxInteret=this.calculerTauxInteret(MethodInvestissement,amountInves,duree);
        BigDecimal amm = amountInves.multiply(tauxInteret);
        BigDecimal durartion= BigDecimal.valueOf(duree);
        BigDecimal amdure=amm.multiply(durartion);
        BigDecimal tdure=tauxInteret.multiply(durartion);
        BigDecimal divpres=tdure.add(new BigDecimal("36500"));
        if (MethodInvestissement == com.example.microgrowth.DAO.Entities.MethodInvestissement.PLACEMENT_POSCOMPTE) {
            Interet=amdure.divide(new BigDecimal("36500"));
        };

        if (MethodInvestissement == com.example.microgrowth.DAO.Entities.MethodInvestissement.PLACEMENT_PRECOMPTE) {
            Interet=amdure.divide(divpres);


        }
        return Interet;
    }



    private BigDecimal obtenirTauxInteretTMM() {

        return new BigDecimal("8.02");
    }

/////////////////*****************notification et confirmation par mail***************////
    public void confirmerInvestissement(Investment inv,String userEmail) {

         investmentRepository.save(inv);
        sendNotificationEmail(userEmail);
    }
    public void sendNotificationEmail(String userEmail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Notification d'investissement confirmé");
        msg.setText("Félicitations! Votre investissement a été bien confirmé");
        msg.setTo(userEmail);
        msg.setFrom("omezzinemariem@gmail.com");

        // Envoyer le message
        JavaMailSender.send(msg);
        System.out.println("email sent succefully");
    }

}










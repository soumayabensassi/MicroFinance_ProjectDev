package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.*;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.DAO.Repositories.PenaliteRepository;
import com.example.microgrowth.DAO.Repositories.TransactionRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Interfaces.ICredit;
import com.example.microgrowth.Service.Interfaces.IPenalite;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class CreditService implements ICredit {
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Credit add_credit_user(Credit c) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(c.getObtainingDate());
// Ajouter 30 jours à la date
        calendar.add(Calendar.DATE, 30);
        Date dateApresAjout = calendar.getTime();
        c.setPack(false);
        c.setDateEcheance(dateApresAjout);
        c.setState(1); //1:en cours  0:refus  2:accordé
        Date date_now = new Date();
        c.setDemandDate(date_now);
        //float ca=calcul_taux(c.getAmount_credit(),c.getDuree() );
        ///c.setIntrestRaiting(ca);
        return creditRepository.save(c);
    }

    @Override
    public Credit add_credit_admin(Credit c) {
        c.setPack(true);

        return creditRepository.save(c);
    }

    @Override
    public Credit edit(Credit c) {
        return creditRepository.save(c);
    }

    @Override
    public List<Credit> selectAll() {
        return creditRepository.findAll();
    }

    @Override
    public Credit SelectById(int id_credit) {
        return creditRepository.findById(id_credit).get();
    }

    @Override
    public void deleteById(int id_credit) {
        creditRepository.deleteById(id_credit);
    }

    @Override
    public List<Credit> SelectByEmail(String email) {
        return creditRepository.findByUsersEmail(email);
    }


    @Override
    public int scoreCredit(int id) {
        int score = 0;
        Credit credit = creditRepository.findById(id).orElse(null);
        User user = userRepository.findById(credit.getUsers().getIdUser()).orElse(null);
        float revenuMensuel = user.getSalaire();
        int ancienneteEmploi = user.getAncienneteEmploi();
        float montantPret = credit.getAmount_credit();
        int dureePret = credit.getDuree();
        float ratioEndettement = credit.getIntrestRaiting();
        Date date_now = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String yearString = yearFormat.format(date_now);
        int year1 = Integer.parseInt(yearString);

        SimpleDateFormat yearFormat2 = new SimpleDateFormat("yyyy");
        String yearString2 = yearFormat.format(user.getDateNaissance());
        int year2 = Integer.parseInt(yearString2);
        int age = year1 - year2;
        boolean garanties = credit.isGaranties();
        boolean historiqueCredit = user.isHistoriqueCredit();


        //Critère 1: Revenu mensuel
        if (revenuMensuel >= 3000 && revenuMensuel <= 5000) {
            score += 20;
        } else if (revenuMensuel > 5000) {
            score += 40;
        }

        //Critère 2: Ancienneté de l'emploi
        if (ancienneteEmploi >= 2 && ancienneteEmploi <= 5) {
            score += 10;
        } else if (ancienneteEmploi > 5) {
            score += 20;
        }

        //Critère 3: Montant et durée du prêt
        if (montantPret >= 5000 && montantPret <= 10000 && dureePret <= 24) {
            score += 10;
        } else if (montantPret > 10000 && dureePret > 24) {
            score += 20;
        }

        //Critère 4: Ratio d'endettement
        if (ratioEndettement < 0.3) {
            score += 20;
        } else if (ratioEndettement >= 0.3 && ratioEndettement <= 0.4) {
            score += 10;
        }


        //Critère 6: Age
        if (age >= 25 && age <= 40) {
            score += 10;
        } else if (age > 40 && age <= 50) {
            score += 5;
        }

        //Critère 7: Garanties
        if (garanties) {
            score += 10;
        }

        //Critère 8: Historique de crédit
        if (historiqueCredit) {
            score += 10;
        }

        return score;
    }

    @Override
    public float calcul_taux(float montant, int duree) {
        return 0;
    }

    @Override
    public void calcul_tableau_credit(Credit c) {
        float amt, interest, monthly_payment;
        float[][] matrice = new float[c.getDuree() * 12][4];

        matrice[0][0] = c.getAmount_credit();
        matrice[0][1] = (matrice[0][0] * c.getIntrestRaiting()) / 12;
        matrice[0][2] = matrice[0][0] / (c.getDuree() * 12);
        amt = matrice[0][2];

        matrice[0][3] = amt + matrice[0][1];


        for (int j = 1; j < c.getDuree() * 12; j++) {
            matrice[j][0] = matrice[j - 1][0] - amt;//montant
            matrice[j][1] = (matrice[j][0] * c.getIntrestRaiting()) / 12;//interet
            matrice[j][2] = matrice[0][0] / (c.getDuree() * 12);//amt
            matrice[j][3] = amt + matrice[j][1];//mensualite

        }
        for (int j = 0; j < matrice.length; j++) {
            for (int i = 0; i < 4; i++) {
                System.out.print(matrice[j][i] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public double calcul_Rentabilite_parCredit(Credit c) {

        double Taux_Actualisation = 0.1;
        double Taux_Actualisationparmois = Math.pow(1 + Taux_Actualisation, 0.08333333) - 1;
        double tauxeparmois = Math.pow(1 + c.getIntrestRaiting(), 0.08333333) - 1;
        double S = Math.pow(1 + tauxeparmois, c.getDuree() * 12);
        double tauxdActualisationConverti = Math.pow(1 + Taux_Actualisationparmois, c.getDuree() * 12);
        double Sa = (S - 1) / tauxeparmois;
        double cap = 1 / tauxdActualisationConverti;


        double produit = c.getMonthlyAmount() * Sa * cap;

        return produit - c.getAmount_credit();

    }

    @Override
    public void ajouter_date_30(Credit c) {

        Date date_now = new Date();
        if (date_now.compareTo(c.getDateEcheance()) > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(c.getObtainingDate());
// Ajouter 30 jours à la date
            calendar.add(Calendar.DATE, 30);
            Date date_incremented = calendar.getTime();
            c.setDateEcheance(date_incremented);
            this.edit(c);
            System.out.println("c est bon ");

        }

    }


    @Autowired
    PenaliteRepository penaliteRepository;
    @Autowired
    IPenalite iPenalite;

    @Override
    public void Accorde_penalite(Penalite p) {
        boolean penalite_existe = false;
        List<Credit> listCreditPaye = creditRepository.selectCreditRembourseeParMois();

        List<Penalite> listPenalite = penaliteRepository.findAll();
        //listCreditNonPaye.remove(listCreditPaye);
        for (Credit i : listCreditPaye) {
            System.out.println(i.getIdCredit());
            this.ajouter_date_30(i);
        }
        List<Credit> listCreditNonPaye = creditRepository.findAll();
        listCreditNonPaye.removeIf(element -> listCreditPaye.contains(element));
        for (Credit i : listCreditNonPaye) {
            System.out.println(i.getIdCredit());

            //i.setPenalites(i.getPenalites()+1);
            //i.setMontant_penalites(i.getMonthlyAmount()+i.getMonthlyAmount()*i.getIntrestRaiting());
            for (Penalite pe : listPenalite) {
                if (pe.getCredits() == i) {
                    penalite_existe = true;
                }
            }
            if (penalite_existe == false) {
                p.setDatePenalite(i.getDateEcheance());

                p.setMontant_penalite(i.getMonthlyAmount()+p.getMontant_penalite() + i.getMonthlyAmount() * i.getIntrestRaiting());
                i.setMontant_penalites(p.getMontant_penalite());
                p.setCredits(i);
                p.setPaye(false);
                iPenalite.add(p);
            }

        }

    }
    TransactionRepository transactionRepository;
    @Override
    public void annuler_penalite() {
    List<Credit> listcredit=creditRepository.findAll();
    List<Transaction> listtrasacion=transactionRepository.findAll();
    for (Credit c : listcredit){


    }
    }



}



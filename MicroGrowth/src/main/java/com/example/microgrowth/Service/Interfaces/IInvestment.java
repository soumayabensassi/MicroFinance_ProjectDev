package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.MethodInvestissement;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

public interface IInvestment {
    Investment add(Investment inv);

    List<Investment> selectAll();
    Investment selectById(int  idInvestment );
    void deleteById(int idInvestment);
    Investment modif(Investment inv);


    double calculerTauxInteret(MethodInvestissement methodInvestissement, double amountInves, int duree);

    //////****************calculer l'interet***************//////////////
    double calculerInteret(MethodInvestissement methodInvestissement, double amountInves, int duree);
    public double Revenu_INVISTISSEMENT(Investment investment);
    List<Investment> getInvestmentByAnnee();



}

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
    BigDecimal calculerTauxInteret(MethodInvestissement MethodInvestissement, BigDecimal amountInves, int duree);
    BigDecimal calculerInteret(MethodInvestissement MethodInvestissement,BigDecimal amountInves,int duree);
    public void confirmerInvestissement(Investment inv,String userEmail);
    public void sendNotificationEmail(String userEmail);
    public double Revenu_INVISTISSEMENT(Investment investment);
}

package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Investment;

import java.util.List;

public interface IInvestment {
    Investment add(Investment inv);

    List<Investment> selectAll();
    Investment selectById(int  idInvestment );
    void deleteById(int idInvestment);
    Investment modif(Investment inv);

}

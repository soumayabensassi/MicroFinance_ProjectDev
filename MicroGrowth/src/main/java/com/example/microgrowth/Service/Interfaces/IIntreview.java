package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Intreview;

import java.util.List;

public interface IIntreview {
    Intreview add_intreview(int id_credit,Intreview intreview);
    List<Intreview> ListIntreview();
}

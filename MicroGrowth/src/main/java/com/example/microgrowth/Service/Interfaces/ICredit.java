package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Credit;

import java.util.List;

public interface ICredit {
    Credit add(Credit c);

    Credit edit(Credit c);

    List<Credit> selectAll();

    Credit SelectById(int id_credit);

    void deleteById(int id_credit);
    List<Credit> SelectByEmail(String email);
}

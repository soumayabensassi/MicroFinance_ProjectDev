package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Credit;

import java.util.List;

public interface ICredit {
    Credit add_credit_user(Credit c);
Credit add_credit_admin(Credit c);
    Credit edit(Credit c);

    List<Credit> selectAll();

    Credit SelectById(int id_credit);

    void deleteById(int id_credit);
    List<Credit> SelectByEmail(String email);
}

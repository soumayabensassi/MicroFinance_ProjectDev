package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.BankAccount;


import java.util.List;

public interface IBankAccount {


    BankAccount add(BankAccount a);
    BankAccount edit(BankAccount a);
    List<BankAccount> selectAll();
    BankAccount SelectById(int id);
    void deleteById(int id);
<<<<<<< HEAD
=======
    void resetLimit();

    @Scheduled(cron = "0 0 1 * * ")
     void notifUpdate();

    //void notifUpdate();
>>>>>>> main

}

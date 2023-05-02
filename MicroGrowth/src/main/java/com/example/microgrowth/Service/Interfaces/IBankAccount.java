package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.BankAccount;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.List;

public interface IBankAccount {


    BankAccount add(BankAccount a);
    BankAccount edit(BankAccount a);
    List<BankAccount> selectAll();
    BankAccount SelectById(int id);
    void deleteById(int id);
    void resetLimit();

    @Scheduled(cron = "0 0 1 * * ")
     void notifUpdate();

    //void notifUpdate();

}

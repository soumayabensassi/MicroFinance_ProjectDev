package com.example.microgrowth.Service.Interfaces;


import com.example.microgrowth.DAO.Entities.BankAccount;
import com.example.microgrowth.DAO.Entities.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransaction {
    Transaction add(Transaction a);
    Transaction edit(Transaction a);
    List<Transaction> selectAll();
    Transaction SelectById(int id);


    void deleteById(int id);

    List<Transaction> selectByRib(String Rib);

    void makeDeposit(Transaction t);
    void makeTransfer(Transaction t);
    void makeWithdrawal(Transaction t);

     void makePayment( Transaction t);

}

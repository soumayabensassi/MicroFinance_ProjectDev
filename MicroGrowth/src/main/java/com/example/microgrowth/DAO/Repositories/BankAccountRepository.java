package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> {



    Optional<BankAccount> findBankAccountByRib(String rib);
}

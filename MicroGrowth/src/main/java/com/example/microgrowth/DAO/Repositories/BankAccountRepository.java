package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> {
}

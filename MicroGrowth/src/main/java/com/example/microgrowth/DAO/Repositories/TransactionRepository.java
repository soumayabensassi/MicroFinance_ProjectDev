package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}

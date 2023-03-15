package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Optional<List<Transaction>> findAllByDateTransactionAndRibSource(Date d, String rib);
    List<Transaction> findAllByRibReceiverOrRibSource(String rib1, String rib2);
}
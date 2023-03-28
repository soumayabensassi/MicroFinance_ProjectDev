package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> {

    @Query("SELECT b.Amount FROM BankAccount b WHERE b.rib = :rib")
    float getAmountByRib(@Param("rib") String rib);

}

package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
    @Query(value = "SELECT * FROM investment i WHERE YEAR(i.date_inv) =?1",nativeQuery = true)
    List<Investment> creditParAnnee(int annnee);
}

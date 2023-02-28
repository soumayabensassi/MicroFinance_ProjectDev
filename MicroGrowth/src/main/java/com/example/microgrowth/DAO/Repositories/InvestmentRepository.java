package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
}

package com.example.microgrowth.DAO.Repositories;



import com.example.microgrowth.DAO.Entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CreditRepository  extends JpaRepository<Credit,Integer> {
    List<Credit> findByUsersEmail(String email);
}

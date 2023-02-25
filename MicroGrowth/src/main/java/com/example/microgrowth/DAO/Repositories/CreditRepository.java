package com.example.microgrowth.DAO.Repositories;



import com.example.microgrowth.DAO.Entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit,Integer> {

}

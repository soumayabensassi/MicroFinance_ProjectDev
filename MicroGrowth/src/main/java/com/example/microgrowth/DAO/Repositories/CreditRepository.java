package com.example.microgrowth.DAO.Repositories;



import com.example.microgrowth.DAO.Entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CreditRepository  extends JpaRepository<Credit,Integer> {
    List<Credit> findByUsersEmail(String email);


    @Query(value = "select c.* from credit c where c.rembourse=?1",nativeQuery = true)
    List<Credit> creditRembouse(boolean b);

}

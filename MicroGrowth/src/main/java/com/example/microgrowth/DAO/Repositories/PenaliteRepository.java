package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface PenaliteRepository extends JpaRepository<Penalite,Integer> {
   @Query(value ="select count(penalite.id_penalite) from penalite " +
           "where penalite.credits_id_credit=:id" ,nativeQuery = true)
    int countPenaliteParCredit(@Param("id") int id);
}

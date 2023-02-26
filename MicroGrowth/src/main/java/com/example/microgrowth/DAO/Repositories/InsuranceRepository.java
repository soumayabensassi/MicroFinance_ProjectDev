package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Inssurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Inssurance,Integer> {
}

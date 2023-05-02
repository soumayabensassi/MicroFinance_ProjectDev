package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Interesse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteresseRepository extends JpaRepository<Interesse,Integer> {
    Interesse findByUsersEmailAndTrainingsIdTraining(String email,int i);
}

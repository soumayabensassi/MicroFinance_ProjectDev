package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Interesse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InteresseRepository extends JpaRepository<Interesse,Integer> {
    Interesse findByUsersEmailAndTrainingsIdTraining(String email,int i);
    @Query(value = "SELECT COUNT(l.id_interesse)FROM interesse l WHERE l.trainings_id_training=?1",nativeQuery = true)
    int totalinteresse(int id);
}

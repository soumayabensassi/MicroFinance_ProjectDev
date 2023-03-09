package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Desinteresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesIntresseRepository extends JpaRepository<Desinteresse,Integer> {
    Desinteresse findByUsersEmailAndTrainingsIdTraining(String email,int i);
}

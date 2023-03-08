package com.example.microgrowth.DAO.Repositories;


import com.example.microgrowth.DAO.Entities.NonParticiper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonParticiperRepository extends JpaRepository<NonParticiper,Integer> {
    NonParticiper findByUsersEmailAndTrainingsIdTraining(String email, int i);
}

package com.example.microgrowth.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.microgrowth.DAO.Entities.Participer;
import com.example.microgrowth.DAO.Entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticiperRepository extends JpaRepository<Participer,Integer> {
    Participer findByUsersEmailAndTrainingsIdTraining(String email, int i );
}

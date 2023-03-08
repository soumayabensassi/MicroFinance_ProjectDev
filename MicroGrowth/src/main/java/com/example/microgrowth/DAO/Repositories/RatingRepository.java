package com.example.microgrowth.DAO.Repositories;


import com.example.microgrowth.DAO.Entities.Interesse;
import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.DAO.Entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Rating findByUsersEmailAndTrainingsIdTraining(String email, int i);
}

package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
    Optional<Rating> findByTrainingsAndUsers(Training event, User user);

    //Rating findByUsersEmailAndTrainingsIdTraining(String email,int i);
    Rating findByUsersIdUserAndTrainingsIdTraining(int userId, int itemId);
    //void deleteById(int ratingId, int userId);

}

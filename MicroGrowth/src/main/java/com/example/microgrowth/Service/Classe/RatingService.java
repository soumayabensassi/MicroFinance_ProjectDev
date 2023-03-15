package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.RatingRepository;
import com.example.microgrowth.Service.Interfaces.IRating;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RatingService implements IRating {
    private RatingRepository ratingRepository;
    @Override
    public Rating add(Rating i) {
        return null;
    }

    @Override
    public void deleteById(int i) {

    }

    @Override
    public Rating verifRating(String email, int i) {
        return ratingRepository.findByUsersEmailAndTrainingsIdTraining(email, i);
    }
    @Override
    public void deleteEventRating(int idtraining, String email) {
        Rating eventRating = ratingRepository.findByUsersEmailAndTrainingsIdTraining( email,idtraining);
        if (eventRating != null) {
            ratingRepository.delete(eventRating);
        }
    }
    @Override
    public void editEventRating(int idtraining, String email) {
        Rating eventRating = ratingRepository.findByUsersEmailAndTrainingsIdTraining( email,idtraining);
        if (eventRating != null) {
            ratingRepository.save(eventRating);
        }
    }


}

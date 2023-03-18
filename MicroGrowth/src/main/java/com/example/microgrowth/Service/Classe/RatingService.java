package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.DAO.Repositories.RatingRepository;
import com.example.microgrowth.Service.Interfaces.IRating;

public class RatingService implements IRating {
    RatingRepository ratingRepository;
    @Override
    public Rating add(Rating i) {
        return null;
    }

    @Override
    public void deleteById(int i) {

    }

    @Override
    public Rating verifRating(String email, int i) {
        return null;
    }

    @Override
    public void deleteEventRating(int idtraining, String email) {

    }

    @Override
    public void editEventRating(int idtraining, String email) {

    }

    @Override
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }
}

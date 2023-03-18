package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Interesse;
import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.DAO.Entities.Training;

public interface IRating {
    Rating add(Rating i);
    void deleteById(int i);
    Rating verifRating(String email, int i);
     void deleteEventRating(int idtraining, String email);
     void editEventRating(int idtraining, String email);
    void delete(Rating rating);
}

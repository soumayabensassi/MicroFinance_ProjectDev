package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Interesse;
import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.DAO.Entities.Training;

public interface IRating {
    Rating add(Rating r);
    void deleteById(int r);
    Rating verifRating(String email, int r);
}

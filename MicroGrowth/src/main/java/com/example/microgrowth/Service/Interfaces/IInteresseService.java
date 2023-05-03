package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Interesse;

public interface IInteresseService {
    Interesse add(Interesse i);
    void deleteById(int i);
    Interesse verifInteresse(String email, int i);
    public int totalInteresse(int id);
}

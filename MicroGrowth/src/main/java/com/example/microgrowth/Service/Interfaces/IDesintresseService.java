package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Desinteresse;

public interface IDesintresseService {
    Desinteresse add(Desinteresse di);
    void deleteById(int id);
    Desinteresse verifDesinsteresse(String email,int i);
}

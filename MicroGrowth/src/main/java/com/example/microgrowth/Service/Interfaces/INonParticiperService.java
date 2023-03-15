package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.NonParticiper;

public interface INonParticiperService {
    NonParticiper add(NonParticiper np);
    void deleteById(int id);
    NonParticiper verifNonParticip(String emain, int i);

}

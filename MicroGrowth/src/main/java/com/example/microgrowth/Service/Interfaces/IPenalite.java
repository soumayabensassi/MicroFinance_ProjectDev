package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Penalite;

import java.util.List;

public interface IPenalite {
    Penalite add(Penalite a);

    Penalite edit(Penalite a);

    void deleteById(int id);
    void annuler_penalite(Penalite a);
    double statistique_penalite_mois(int mois);
    List<Penalite> afficherpenalite();

}

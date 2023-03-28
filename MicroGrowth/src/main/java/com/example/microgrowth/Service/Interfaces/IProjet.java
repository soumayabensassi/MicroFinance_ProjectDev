package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.Projet;

import java.util.List;

public interface IProjet {
    Projet add(Projet inv);

    List<Projet> selectAll();
    Projet selectById(Long  idprojet );
    void deleteById(Long idprojet);
    Projet modif(Projet projet);
    public  double calculerRendementAnnuel(double investissementInitial, Double tauxRendement);
    public void recalculerObligation(Long projetId, double investissement);
}

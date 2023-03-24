package com.example.microgrowth.Service.Classe;
import com.example.microgrowth.DAO.Repositories.ProjetRepository;
import com.example.microgrowth.DAO.Entities.Projet;
import com.example.microgrowth.Service.Interfaces.IProjet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ProjetService implements IProjet {
     ProjetRepository projetRepository;
    @Override
    public Projet add(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public List<Projet> selectAll() {
        return projetRepository.findAll();
    }

    @Override
    public  Projet selectById(Long idprojet) {
        return projetRepository.findById(idprojet).orElse(null);
    }

    @Override
    public void deleteById(Long idprojet) {
        projetRepository.deleteById(idprojet);

    }

    @Override
    public Projet modif(Projet projet) {
        return projetRepository.save(projet);
    }
    public  double calculerRendementAnnuel(double investissementInitial, Double tauxRendement) {
        double gainAnnuel = investissementInitial * tauxRendement;
        return investissementInitial + gainAnnuel;
    }
    public void recalculerObligation(Long projetId, double investissement){

        Projet projet = projetRepository.findById(projetId).orElse(null);
        double nouvelleObligation = projet.getObligation() - investissement;
        String numberAsString = String.valueOf(nouvelleObligation);
        System.out.printf(numberAsString);
        projet.setObligation(nouvelleObligation);
        projetRepository.save(projet);

    }



}

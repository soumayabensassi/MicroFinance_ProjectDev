package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Penalite;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.DAO.Repositories.PenaliteRepository;
import com.example.microgrowth.Service.Interfaces.IPenalite;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PenaliteService implements IPenalite {
    PenaliteRepository penaliteRepository;
    CreditRepository creditRepository;
    @Override
    public Penalite add(Penalite a) {
        return penaliteRepository.save(a);
    }

    @Override
    public Penalite edit(Penalite a) {
        return penaliteRepository.save(a);
    }
    @Override
    public void deleteById(int id) {
        penaliteRepository.deleteById(id);
    }

    @Override
    public void annuler_penalite(Penalite a) {
        a.setMontant_penalite(0);
        a.setPaye(true);
    }

    @Override
    public double statistique_penalite_mois(int mois) {
        int all=penaliteRepository.CountAllPenalite();
        System.out.println(all);
        int nb=penaliteRepository.NombrePenaliteParMois(mois);
        System.out.println(nb);
        double ratio = (double) nb / all;
        System.out.println(ratio);
        return ratio;


    }

    @Override
    public List<Penalite> afficherpenalite() {
        return penaliteRepository.findAll();
    }


}

package com.example.microgrowth.Service.Classe;
import com.example.microgrowth.DAO.Repositories.ProjetRepository;
import com.example.microgrowth.DAO.Entities.Projet;
import com.example.microgrowth.Service.Interfaces.IProjet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjetService implements IProjet {
     ProjetRepository projetRepository;

    @Override
    public void add(Projet projet) {
         projetRepository.save(projet);


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
    public  double calculerIntrestStocks(double currentPrice,double purchasePrice,int numShares,double dividendYield ,int holdingPeriod ){

    Double capitalGains = (currentPrice - purchasePrice) / purchasePrice;
    Double dividends = dividendYield * purchasePrice * numShares;
    Double totalReturn = capitalGains + dividends;

    // Calculate the annual return
    Double annualReturn = Math.pow(1 + totalReturn, 1.0 / holdingPeriod) - 1;

        return annualReturn;
}

}

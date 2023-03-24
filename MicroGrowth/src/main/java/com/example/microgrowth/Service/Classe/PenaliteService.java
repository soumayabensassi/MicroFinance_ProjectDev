package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Penalite;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.DAO.Repositories.PenaliteRepository;
import com.example.microgrowth.Service.Interfaces.IPenalite;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    }


}

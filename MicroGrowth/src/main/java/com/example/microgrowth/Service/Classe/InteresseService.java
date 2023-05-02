package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Interesse;
import com.example.microgrowth.DAO.Repositories.InteresseRepository;
import com.example.microgrowth.Service.Interfaces.IInteresseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InteresseService implements IInteresseService {
    private InteresseRepository interesseRepository;
    @Override
    public Interesse add(Interesse i) {
        i.setNbrI(1);
        return interesseRepository.save(i);
    }

    @Override
    public void deleteById(int i) {
        interesseRepository.deleteById(i);

    }

    @Override
    public Interesse verifInteresse(String email, int i) {
        return interesseRepository.findByUsersEmailAndTrainingsIdTraining(email, i);
    }
}

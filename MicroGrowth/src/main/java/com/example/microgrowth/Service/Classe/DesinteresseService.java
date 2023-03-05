package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Desinteresse;
import com.example.microgrowth.DAO.Repositories.DesIntresseRepository;
import com.example.microgrowth.Service.Interfaces.IDesintresseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DesinteresseService implements IDesintresseService {
    private DesIntresseRepository desIntresseRepository;
    @Override
    public Desinteresse add(Desinteresse di) {
        di.setNbrnonparticip(1);
        return desIntresseRepository.save(di);
    }

    @Override
    public void deleteById(int id) {
        desIntresseRepository.deleteById(id);

    }

    @Override
    public Desinteresse verifDesinsteresse(String email, int i) {
        return desIntresseRepository.findByUsersEmailAndTrainingsIdTraining(email, i);
    }
}

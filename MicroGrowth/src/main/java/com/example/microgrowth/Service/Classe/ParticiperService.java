package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Participer;
import com.example.microgrowth.DAO.Repositories.ParticiperRepository;
import com.example.microgrowth.Service.Interfaces.IParticiperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ParticiperService implements IParticiperService {
    private ParticiperRepository participerRepository;

    @Override
    public Participer add(Participer p) {
        p.setNbrP(1);
        return participerRepository.save(p);
    }

    @Override
    public void deleteById(int id) {
        participerRepository.deleteById(id);

    }

    @Override
    public Participer verifParticip(String email, int i) {
        return participerRepository.findByUsersEmailAndTrainingsIdTraining(email, i);
    }
}

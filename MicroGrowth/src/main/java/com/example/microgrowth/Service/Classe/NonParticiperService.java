package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.NonParticiper;
import com.example.microgrowth.DAO.Repositories.NonParticiperRepository;
import com.example.microgrowth.Service.Interfaces.INonParticiperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NonParticiperService implements INonParticiperService {
    private NonParticiperRepository nonParticiperRepository;
    @Override
    public NonParticiper add(NonParticiper np) {
        np.setNbrNP(1);
        return nonParticiperRepository.save(np);
    }

    @Override
    public void deleteById(int id) {
        nonParticiperRepository.deleteById(id);

    }

    @Override
    public NonParticiper verifNonParticip(String email, int i) {
        return nonParticiperRepository.findByUsersEmailAndTrainingsIdTraining(email, i);
    }
}

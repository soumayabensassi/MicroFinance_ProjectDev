package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Repositories.TrainingRepository;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TrainingService implements ITrainingService {

    private TrainingRepository trainingRepository;
    @Override
    public Training add(Training training) {

        return trainingRepository.save(training);
    }

    @Override
    public Training edit(Training training) {

        return trainingRepository.save(training);
    }

    @Override
    public List<Training> selectAll() {

        return trainingRepository.findAll();
    }

    @Override
    public Training selectById(int idtraining) {

        return trainingRepository.findById(idtraining).orElse(null);
    }

    @Override
    public void deleteById(int idtraining) {
           trainingRepository.deleteById(idtraining);
    }

    @Override
    public void delete(Training training) {
        trainingRepository.delete(training);

    }

    @Override
    public List<Training> addAll(List<Training> listT) {

        return trainingRepository.saveAll(listT);
    }

    @Override
    public void deleteAll(List<Training> listT) {
        trainingRepository.deleteAll();

    }
}

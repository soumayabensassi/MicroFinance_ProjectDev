package com.example.microgrowth.Service.Interfaces;


import com.example.microgrowth.DAO.Entities.Training;

import java.util.List;

public interface ITrainingService {
    Training add(Training training);
    Training edit(Training training);
    List<Training> selectAll();
    Training selectById(int idtraining);
    void deleteById(int idtraining);
    void delete(Training training);
    List<Training> addAll(List<Training> listT);
    void deleteAll(List<Training> listT);
}

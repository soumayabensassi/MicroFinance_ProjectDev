package com.example.microgrowth.Service.Interfaces;


import com.example.microgrowth.DAO.Entities.Training;

import javax.mail.MessagingException;
import java.util.List;
import java.util.ListResourceBundle;

public interface ITrainingService {
    Training add(Training training);
    Training edit(Training training);
    List<Training> selectAll();
    Training selectById(int trainingId);
    void deleteById(int idtraining);
    void delete(Training training);
    List<Training> addAll(List<Training> listT);
    void deleteAll(List<Training> listT);
    void deleteByDate();
    void sendMailExpiration() throws MessagingException;
    List<Training> selectByDate();
    List<Training>findwithsorting(String field,String type);


}

package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.TrainingRepository;
import com.example.microgrowth.Service.Interfaces.ITrainingService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class TrainingService implements ITrainingService {
    @Autowired
    private EmailSenderService senderService;
    //@Autowired
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

    }

    @Override
    public void deleteByDate() {
        List<Training> listT = trainingRepository.selectByDate();
        trainingRepository.deleteAll(listT);

    }

    @Override
    public void sendMailExpiration() throws MessagingException {
        //List<User> listU = trainingRepository.selectUsers();

        for(User us : trainingRepository.selectUsers()) {
            senderService.sendEmail(us.getEmail(), " Evenement PréExpiré", "Evenement Expiré", "C:/Users/HP/Documents/mir.pdf");
        }
    }

    @Override
    public List<Training> selectByDate() {
        return trainingRepository.selectByDate();
    }

}

package com.example.microgrowth.RestControllers;


import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.TrainingSortCriteria;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.TrainingRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Classe.EmailSenderService;
import com.example.microgrowth.Service.Classe.TrainingService;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class TrainingRestController {
    private ITrainingService iTrainingService;
    private TrainingRepository trainingRepository ;
    private UserRepository userRepository;
    @GetMapping("/afficherT")
    public List<Training> afficherT(){
        return
                iTrainingService.selectAll();
    }
    @Autowired
    private EmailSenderService senderService;
    @Autowired
    private TrainingService trainingService;

    //@EventListener(ApplicationReadyEvent.class)
    @PostMapping("/ajouterTraining")
    public ResponseEntity<?>ajouterT(@RequestBody Training training) throws MessagingException {

        for(User us : trainingRepository.selectUsers()) {
            senderService.sendEmail(us.getEmail(), " Nouvel Evenement ", "Nouvel Evenement ", "C:/Users/HP/Documents/mir.pdf");
        }
        iTrainingService.add(training);
        return ResponseEntity.status(HttpStatus.OK).body("ajout done");
    }
    //public Training ajouterT(@RequestBody Training training) throws MessagingException {
        //senderService.sendEmail("myriam.brahmi@esprit.tn","This is Subject","This is Body","C:/Users/HP/Documents/lettre-motivation-mimi");
        //return iTrainingService.add(training);
    //}
    @GetMapping("/afficherAvecIdT/{idtraining}")
    public Training afficherAvecIdT(@PathVariable int idtraining){
        return iTrainingService.selectById(idtraining);
    }
    @DeleteMapping("/deleteTraining/{idtraining}")
    public void supprimerAvecIdT(@PathVariable int idtraining){
        iTrainingService.deleteById(idtraining);
    }
    @DeleteMapping("/deleteTraining")
    public void supprimerT(Training training){
        iTrainingService.delete(training);
    }
    @PutMapping("/editTraining")
    public Training modifier(@RequestBody Training training)
    {return iTrainingService.edit(training);
    }
    @DeleteMapping("/deleteExpTraining")
    public void supprimerTE() throws MessagingException {


        iTrainingService.deleteByDate( );
        for(User us : trainingRepository.selectUsers()) {
            senderService.sendEmail(us.getEmail(), " Evenement Expiré", "Evenement Expiré", "C:/Users/HP/Documents/mir.pdf");
        }
    }
    @GetMapping("/afficherPresExpiredT")
    public List<Training> afficherPresExpiredT() throws MessagingException {
         iTrainingService.sendMailExpiration();
        return
                iTrainingService.selectByDate();
    }
    @GetMapping("/afficherTs/{field}/{type}")
    public List<Training> afficherTs(@PathVariable String field,@PathVariable String type){
        return iTrainingService.findwithsorting(field,type);
    }




    @GetMapping("/trainings/{sortCriteria1}/{ascending1}/{sortCriteria2}/{ascending2}")
    public List<Training> getTrainings(@PathVariable TrainingSortCriteria sortCriteria1,
                                 @PathVariable boolean ascending1,@PathVariable TrainingSortCriteria sortCriteria2,
                                    @PathVariable boolean ascending2) {
        List<Training> trainings = trainingRepository.findAll(); // Récupérer les événements à partir d'une source de données
        if (sortCriteria1 != null && sortCriteria2 != null) {
            trainings = trainingService.sortEvents(trainings, sortCriteria1, ascending1);
            trainings = trainingService.sortEvents(trainings, sortCriteria2, ascending2);
        }
        return trainings;
    }
    @GetMapping("/trainings/{sortCriteria}/{ascending}")
    public List<Training> getTraining(@PathVariable TrainingSortCriteria sortCriteria,
                                    @PathVariable boolean ascending) {
        List<Training> trainings = trainingRepository.findAll(); // Récupérer les événements à partir d'une source de données
        if (sortCriteria != null ) {
            trainings = trainingService.sortEvents(trainings, sortCriteria, ascending);

        }
        return trainings;
    }
    @GetMapping("/training/search/{title}/{price}/{subject}")
    public List<Training> searchTrainings(
            @PathVariable(required = false) String title,
            @PathVariable(required = false) float price,
            @PathVariable(required = false) String subject) {

        Stream<Training> trainingStream = trainingService.searchTraining(title, price, subject);

        List<Training> trainingList = trainingStream.collect(Collectors.toList());

        return trainingList;
    }
    @GetMapping("/training/searcht/{title}")
    public List<Training> searchT1(
            @PathVariable(required = false) String title) {

        Stream<Training> trainingStream = trainingService.searchTraining1(title);

        List<Training> trainingList = trainingStream.collect(Collectors.toList());

        return trainingList;
    }
    @GetMapping("/training/searchs/{subject}")
    public List<Training> searchT2(
            @PathVariable(required = false) String subject) {

        Stream<Training> trainingStream = trainingService.searchTraining2(subject);

        List<Training> trainingList = trainingStream.collect(Collectors.toList());

        return trainingList;
    }
    @GetMapping("/training/searchp/{price}")
    public List<Training> searchT3(

            @PathVariable(required = false) float price) {

        Stream<Training> trainingStream = trainingService.searchTraining3( price);

        List<Training> trainingList = trainingStream.collect(Collectors.toList());

        return trainingList;
    }

}

package com.example.microgrowth.RestControllers;


import com.example.microgrowth.DAO.Entities.Complaint;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.TrainingRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Classe.EmailSenderService;
import com.example.microgrowth.Service.Classe.RatingService;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.mail.MessagingException;
import java.awt.*;
import java.util.Date;
import java.util.List;
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
        return
                iTrainingService.findwithsorting(field,type);
    }


}

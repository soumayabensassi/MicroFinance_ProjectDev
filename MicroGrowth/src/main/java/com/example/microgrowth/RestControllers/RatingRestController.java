package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Interesse;
import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.RatingRepository;
import com.example.microgrowth.DAO.Repositories.TrainingRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Classe.RatingService;
import com.example.microgrowth.Service.Classe.TrainingService;
import com.example.microgrowth.Service.Classe.UserService;
import com.example.microgrowth.Service.Interfaces.IRating;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j

public class RatingRestController {
    @Autowired
    private  TrainingRepository trainingRepository;
    @Autowired
    private  RatingRepository ratingRepository;
    private ITrainingService iTrainingService;
    private TrainingService trainingService;
    private IUser iUser;
    private IRating iRating;
    private UserService userService;
    private RatingService ratingService;
    //private final UserRepository userRepository;

    /*public RatingRestController(TrainingRepository trainingRepository, RatingRepository ratingRepository, UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }*/

    @PostMapping("/ratings/{email}/{trainingId}")
    public Rating createRating(@PathVariable String email,@PathVariable int trainingId, @RequestBody Rating rating) {
        Rating rating1 = iRating.verifRating(email, trainingId);

            Training training = trainingRepository.findById(trainingId).get();
            Rating rating2=new Rating(iUser.getUserByEmail(email),iTrainingService.selectById(trainingId));
            rating.setTrainings(training);
            //if (rating2.getScore()<=5)
             return ratingRepository.save(rating2);


    }
    @DeleteMapping("/deleteratings/{email}/{trainingId}/{ratingId}")
    public void deleteEventRating(@PathVariable int trainingId, @PathVariable String email,@PathVariable int ratingId) {
        Training event = trainingService.selectById(trainingId);
        User user = userService.getUserByEmail(email);
        ratingService.deleteEventRating(ratingId, email);
    }

    @PutMapping("/editratings/{email}/{trainingId}/{idrating}")
    public void editEventRating(@PathVariable int trainingId, @PathVariable String email,@PathVariable int idrating) {
        Training event = trainingService.selectById(trainingId);
        User user = userService.getUserByEmail(email);
        Rating rating2=new Rating(iUser.getUserByEmail(email),iTrainingService.selectById(trainingId),idrating);

        ratingRepository.save(rating2);
    }
}

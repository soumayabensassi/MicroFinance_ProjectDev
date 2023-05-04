package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.RatingRepository;
import com.example.microgrowth.DAO.Repositories.TrainingRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Classe.TrainingService;
import com.example.microgrowth.Service.Classe.UserService;
import com.example.microgrowth.Service.Interfaces.IMicroGrowth;
import com.example.microgrowth.Service.Interfaces.IRating;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import com.example.microgrowth.Service.Interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
//@AllArgsConstructor
//@Slf4j
//@RequestMapping("/ratings")
public class RatingRestController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
   @Autowired
    private IMicroGrowth iMicroGrowth;

    @PostMapping("/user/ratings/{trainingId}/{score}")
    public ResponseEntity<?> createRating(@PathVariable int trainingId,  @PathVariable int score) {
        System.out.println("email");
        String email=iMicroGrowth.getCurrentUserName();
        System.out.println(email);
        Training event = trainingRepository.findById(trainingId);
        User user = userRepository.findByEmail(email);

        Optional<Rating> existingRating = ratingRepository.findByTrainingsAndUsers(event, user);
        if (existingRating.isPresent()) {
            return ResponseEntity.badRequest().body("User already rated this event");
        }

        Rating rating = new Rating();
        rating.setTrainings(event);
        rating.setUsers(user);
        rating.setScore(score);
        if (rating.getScore()<=5 && rating.getScore()>=0 && rating!=null){
        ratingRepository.save(rating);
            return ResponseEntity.ok().build();}
        else
            return ResponseEntity.notFound().build();


    }
    @DeleteMapping("/user/deleteratings/{trainingId}")
    public ResponseEntity<?> deleteEventRating(@PathVariable int trainingId) {
        String email=iMicroGrowth.getCurrentUserName();
        Training event = trainingRepository.findById(trainingId);
        User user = userRepository.findByEmail(email);
        //int rating=ratingRepository.findById(ratingId).get().getId();
        Rating rating=new Rating();
        Optional<Rating> existingRating = ratingRepository.findByTrainingsAndUsers(event, user);

        if (existingRating.isPresent()) {
             ratingRepository.delete(existingRating.get());
            return ResponseEntity.ok().build();
        }else
        return ResponseEntity.badRequest().body("Rating already deleted");
    }
}

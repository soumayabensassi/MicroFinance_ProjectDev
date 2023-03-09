package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.NonParticiper;
import com.example.microgrowth.DAO.Entities.Participer;
import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.Service.Interfaces.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class RatingRestController {
    private IUser iUser;
    private IRating iRating;

    private ITrainingService iTrainingService;
    @PostMapping("/Rating/{email}/{idtraining}")
    public void ratingtraining(@PathVariable String email, @PathVariable int idtraining)
    {
        Rating rating=iRating.verifRating(email,idtraining);

        log.info("likes is : {}",rating);

            Rating rating1=new Rating(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iRating.add(rating1);


}}

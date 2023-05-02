package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Desinteresse;
import com.example.microgrowth.DAO.Entities.Interesse;
import com.example.microgrowth.Service.Interfaces.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@Slf4j
public class InteresseRestController {
    private IUser iUser;
    private IInteresseService iInteresseService;
    private IDesintresseService iDesintresseService;
    private ITrainingService iTrainingService;
    private IMicroGrowth iMicroGrowth;

    @PostMapping("/user/Interessetraining/{idtraining}/{email}")
    public void Interessetraining( @PathVariable int idtraining,@PathVariable String email)
    {
        //String email=iMicroGrowth.getCurrentUserName();
        Interesse interesse=iInteresseService.verifInteresse(email,idtraining);
        Desinteresse desinteresse=iDesintresseService.verifDesinsteresse(email,idtraining);
        log.info("likes is : {}",interesse);
        if (interesse==null && desinteresse == null) {
            Interesse interesse1=new Interesse(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iInteresseService.add(interesse1);
        } else if (interesse==null && desinteresse!=null) {
            iDesintresseService.deleteById(desinteresse.getIdDesinteresse());
            Interesse interesse1=new Interesse(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iInteresseService.add(interesse1);
        } else  iInteresseService.deleteById(interesse.getIdInteresse());
    }
}

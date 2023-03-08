package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Desinteresse;
import com.example.microgrowth.DAO.Entities.Interesse;
import com.example.microgrowth.Service.Interfaces.IDesintresseService;
import com.example.microgrowth.Service.Interfaces.IInteresseService;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.crypto.Des;

@RestController
@AllArgsConstructor
@Slf4j
public class InteresseRestController {
    private IUser iUser;
    private IInteresseService iInteresseService;
    private IDesintresseService iDesintresseService;
    private ITrainingService iTrainingService;

    @PostMapping("/Interessetraining/{email}/{idtraining}")
    public void Interessetraining(@PathVariable String email, @PathVariable int idtraining)
    {
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

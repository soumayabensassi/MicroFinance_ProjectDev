package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Desinteresse;
import com.example.microgrowth.DAO.Entities.Interesse;
import com.example.microgrowth.DAO.Repositories.DesIntresseRepository;
import com.example.microgrowth.Service.Interfaces.IDesintresseService;
import com.example.microgrowth.Service.Interfaces.IInteresseService;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
// import sun.security.krb5.internal.crypto.Des;

@RestController
@AllArgsConstructor
@Slf4j
public class DesinteresseRestController {
    private IUser iUser;
    private IDesintresseService iDesintresseService;
    private IInteresseService iInteresseService;
    private ITrainingService iTrainingService;

    @PostMapping("/Desinteresse/{email}/{idtraining}")

    public void Desiniteressetraining(@PathVariable String email,@PathVariable int idtraining)
    {
        Interesse interesse=iInteresseService.verifInteresse(email,idtraining);
        Desinteresse desinteresse=iDesintresseService.verifDesinsteresse(email,idtraining);
        if (interesse==null && desinteresse == null) {
            Desinteresse d=new Desinteresse(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iDesintresseService.add(d);
        } else if (interesse!=null && desinteresse==null) {
            iInteresseService.deleteById(interesse.getIdInteresse());
            Desinteresse d=new Desinteresse(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iDesintresseService.add(d);
        } else  iDesintresseService.deleteById(desinteresse.getIdDesinteresse());
    }

}

package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.NonParticiper;
import com.example.microgrowth.DAO.Entities.Participer;
import com.example.microgrowth.Service.Interfaces.INonParticiperService;
import com.example.microgrowth.Service.Interfaces.IParticiperService;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ParticiperRestController {

    private IUser iUser;
    private IParticiperService iParticiperService;
    private INonParticiperService iNonParticiperService;
    private ITrainingService iTrainingService;
    @PostMapping("/Participer/{email}/{idtraining}")
    public void participertraining(@PathVariable String email, @PathVariable int idtraining)
    {
        Participer participer=iParticiperService.verifParticip(email,idtraining);
        NonParticiper nonParticiper=iNonParticiperService.verifNonParticip(email,idtraining);
        log.info("likes is : {}",participer);
        if (participer==null && nonParticiper == null) {
            Participer participer1=new Participer(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iParticiperService.add(participer1);
        } else if (participer==null && nonParticiper!=null) {
            iNonParticiperService.deleteById(nonParticiper.getIdNonParticiper());
            Participer participer1=new Participer(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iParticiperService.add(participer1);
        } else  iParticiperService.deleteById(participer.getIdParticiper());
    }
}

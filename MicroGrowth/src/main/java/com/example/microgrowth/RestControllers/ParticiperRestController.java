package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.NonParticiper;
import com.example.microgrowth.DAO.Entities.Participer;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.Service.Interfaces.*;
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
    private IMicroGrowth iMicroGrowth;
    @PostMapping("/user/Participer/{idtraining}/{email}")
    public void participertraining( @PathVariable int idtraining,@PathVariable String email)
    {
        //String email=iMicroGrowth.getCurrentUserName();
        Participer participer=iParticiperService.verifParticip(email,idtraining);
        NonParticiper nonParticiper=iNonParticiperService.verifNonParticip(email,idtraining);
        Training p=iTrainingService.selectById(idtraining);
        log.info("likes is : {}",participer);
        if (participer==null && nonParticiper == null) {
            Participer participer1=new Participer(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iParticiperService.add(participer1);
            p.setNombreParticiper(iParticiperService.totalParticiper(idtraining));
            iTrainingService.edit(p);
        } else if (participer==null && nonParticiper!=null) {
            iNonParticiperService.deleteById(nonParticiper.getIdNonParticiper());
            Participer participer1=new Participer(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iParticiperService.add(participer1);
        } else  iParticiperService.deleteById(participer.getIdParticiper());
    }
}

package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.NonParticiper;
import com.example.microgrowth.DAO.Entities.Participer;
import com.example.microgrowth.Service.Interfaces.INonParticiperService;
import com.example.microgrowth.Service.Interfaces.IParticiperService;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NonParticiperRestController {
    private INonParticiperService iNonParticiperService;
    private IParticiperService iParticiperService;
    private IUser iUser;
    private ITrainingService iTrainingService;

    @PostMapping("/NonParticiper/{email}/{idtraining}")
    public void Nonparticipertraining(@PathVariable String email, @PathVariable int idtraining)
    {
        Participer participer=iParticiperService.verifParticip(email,idtraining);
        NonParticiper nonParticiper=iNonParticiperService.verifNonParticip(email,idtraining);
        if (participer==null && nonParticiper == null) {
            NonParticiper d=new NonParticiper(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iNonParticiperService.add(d);
        } else if (participer!=null && nonParticiper==null) {
            iParticiperService.deleteById(participer.getIdParticiper());
            NonParticiper d=new NonParticiper(iUser.getUserByEmail(email),iTrainingService.selectById(idtraining));
            iNonParticiperService.add(d);
        } else  iNonParticiperService.deleteById(nonParticiper.getIdNonParticiper());
    }
}

package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.DAO.Repositories.PenaliteRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Classe.EmailService;
import com.example.microgrowth.Service.Interfaces.ICredit;
import com.example.microgrowth.Service.Interfaces.IPenalite;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PenaliteRestController {
    PenaliteRepository penaliteRepository;
    IPenalite iPenalite;
    ICredit iCredit;
    UserRepository userRepository;
    private EmailService emailService;
    CreditRepository creditRepository;

    @GetMapping("/SendPenaliteEmail/{id}")
    void SendEmailPenalite(@PathVariable int id){
        int user_id= creditRepository.SelectUserFromCredit(id);
        User user=userRepository.findById(user_id).get();
        System.out.println(user.getEmail());

        if(penaliteRepository.countPenaliteParCredit(id)==1){

    emailService.sendPenaliteEmail(user.getEmail());
    }

}
}

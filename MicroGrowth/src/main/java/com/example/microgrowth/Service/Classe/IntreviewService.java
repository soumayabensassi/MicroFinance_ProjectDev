package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Intreview;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.DAO.Repositories.IntreviewRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Interfaces.ICredit;
import com.example.microgrowth.Service.Interfaces.IIntreview;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class IntreviewService implements IIntreview {
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IntreviewRepository intreviewRepository;
    @Autowired
    ICredit iCredit;

    @Override
    public Intreview add_intreview(int id_credit, Intreview intreview) {
        String message = "score inferieur à 50";
       intreview.setCredits(creditRepository.findById(id_credit).orElse(null));
        if (iCredit.scoreCredit(id_credit) > 10) {
            return intreviewRepository.save(intreview);
        } else {
            System.out.println(message);
            return null;
        }
    }
}

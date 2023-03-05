package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.Service.Interfaces.ICredit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class CreditService implements ICredit {
    private CreditRepository creditRepository;
    @Override
    public Credit add_credit_user(Credit c) {
        c.setPack(false);
        c.setState(1); //1:en cours  0:refus  2:accordé
        Date date_now = new Date();
        c.setDemandDate(date_now);
        return creditRepository.save(c);
    }

    @Override
    public Credit add_credit_admin(Credit c) {
        c.setPack(true);

        return creditRepository.save(c);
    }

    @Override
    public Credit edit(Credit c) {
        return creditRepository.save(c);
    }

    @Override
    public List<Credit> selectAll() {
        return creditRepository.findAll();
    }

    @Override
    public Credit SelectById(int id_credit) {
        return creditRepository.findById(id_credit).get();
    }

    @Override
    public void deleteById(int id_credit) {
creditRepository.deleteById(id_credit);
    }}


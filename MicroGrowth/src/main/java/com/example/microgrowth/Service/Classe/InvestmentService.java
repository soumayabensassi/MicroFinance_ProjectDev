package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Repositories.InvestmentRepository;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvestmentService implements IInvestment {
    InvestmentRepository investmentRepository;
    @Override
    public Investment add(Investment inv) {
        return  investmentRepository.save(inv);
    }

    @Override
    public List<Investment> selectAll() {
        return investmentRepository.findAll();
    }

    @Override
    public Investment selectById(int idInvestment) {
        return investmentRepository.findById(idInvestment).orElse(null);
    }

    @Override
    public void deleteById(int idInvestment) {
        investmentRepository.deleteById(idInvestment);
    }







    @Override
    public Investment modif(Investment inv) {
        return  investmentRepository.save(inv);
    }
}

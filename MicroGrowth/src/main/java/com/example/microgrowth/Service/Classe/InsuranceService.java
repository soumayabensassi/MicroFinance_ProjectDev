package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Inssurance;
import com.example.microgrowth.DAO.Repositories.InsuranceRepository;
import com.example.microgrowth.Service.Interfaces.IInsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class InsuranceService implements IInsuranceService {

    private InsuranceRepository insuranceRepository;

    @Override
    public Inssurance add(Inssurance inssurance) {
        return insuranceRepository.save(inssurance);
    }

    @Override
    public Inssurance edit(Inssurance inssurance) {
        return insuranceRepository.save(inssurance);
    }

    @Override
    public List<Inssurance> selectAll() {
        return insuranceRepository.findAll();
    }

    @Override
    public Inssurance selectById(int idInsurance) {
        return insuranceRepository.findById(idInsurance).get();
    }

    @Override
    public void delete(Inssurance inssurance) {
        insuranceRepository.delete(inssurance);
    }

    @Override
    public List<Inssurance> addAll(List<Inssurance> inssuranceList) {
        return insuranceRepository.saveAll(inssuranceList);

    }
    @Override
    public void deleteAll(List<Inssurance> inssuranceList) {
        insuranceRepository.deleteAll(inssuranceList);
    }

    @Override
    public void deleteById(int idInsurance) {
        insuranceRepository.deleteById(idInsurance);
    }

}

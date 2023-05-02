package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Inssurance;

import java.util.List;

public interface IInsuranceService {
    Inssurance add(Inssurance inssurance);
    Inssurance edit( Inssurance inssurance);
    List<Inssurance> selectAll();
    Inssurance selectById(int idInsurance);

    void delete (Inssurance inssurance);
    List<Inssurance> addAll(List<Inssurance> inssuranceList);


    void deleteAll(List<Inssurance> inssuranceList);


    void deleteById(int idInsurance);
}

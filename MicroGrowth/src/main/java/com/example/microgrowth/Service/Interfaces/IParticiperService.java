package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Participer;
//import io.swagger.v3.oas.annotations.enums.ParameterIn;

public interface IParticiperService {
    Participer add(Participer p);
    void deleteById(int id);
    Participer verifParticip(String email, int i);
}

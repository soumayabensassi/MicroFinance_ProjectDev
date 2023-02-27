package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Role;

public interface IDislike {
    Dislike add(Dislike a);
    void deleteById(int id);
}

package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Likes;

public interface ILike {
    Likes add(Likes a);
    void deleteById(int id);
}

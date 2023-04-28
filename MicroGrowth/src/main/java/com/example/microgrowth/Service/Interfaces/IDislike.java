package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.DAO.Entities.Role;

public interface IDislike {
    Dislike add(Dislike a);
    void deleteById(int id);
    Dislike verifDislikePublication(String email,int i);
    Dislike verifDislikeComment(String email,int i);
    int totalDisLike(int id);
    int totalDisLikeComment(int id);
}

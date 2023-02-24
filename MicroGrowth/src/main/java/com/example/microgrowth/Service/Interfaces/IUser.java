package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.User;

import java.util.List;

public interface IUser {
    User add(User a);
    User edit(User a);
    List<User> selectAll();
    User SelectById(int id);
    void deleteById(int id);
}

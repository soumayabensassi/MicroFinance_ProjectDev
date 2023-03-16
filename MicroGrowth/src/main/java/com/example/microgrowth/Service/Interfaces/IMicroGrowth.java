package com.example.microgrowth.Service.Interfaces;


import com.example.microgrowth.DAO.Entities.Role;
import com.example.microgrowth.DAO.Entities.User;

import java.util.List;
public interface IMicroGrowth {

    User getUser(String username);
    List<User>getUsers();
}

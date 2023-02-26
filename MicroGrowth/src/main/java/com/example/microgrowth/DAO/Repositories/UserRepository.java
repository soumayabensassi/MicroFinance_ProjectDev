package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}

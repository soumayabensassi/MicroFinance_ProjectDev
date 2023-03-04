package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.password = ?1    WHERE a.email = ?2")
    void resetPassword(String password,String email);
}

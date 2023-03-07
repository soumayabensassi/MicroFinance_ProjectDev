package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikeRepository extends JpaRepository <Dislike,Integer> {
    Dislike findByUsersEmailAndPublicationsIdPublication(String email,int i);
}

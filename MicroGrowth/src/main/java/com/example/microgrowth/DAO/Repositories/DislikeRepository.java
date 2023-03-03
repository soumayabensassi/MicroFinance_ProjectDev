package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikeRepository extends JpaRepository <Dislike,Integer> {
}

package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Integer> {
}

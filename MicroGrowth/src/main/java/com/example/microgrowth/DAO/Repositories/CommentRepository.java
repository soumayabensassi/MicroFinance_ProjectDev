package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByPublicationsIdPublication(int id);
}

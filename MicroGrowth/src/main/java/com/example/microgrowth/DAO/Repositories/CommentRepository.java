package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}

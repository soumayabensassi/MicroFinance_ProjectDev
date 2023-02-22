package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Repositories.CommentRepository;
import com.example.microgrowth.Service.Interfaces.IComment;

import java.util.List;

public class CommentService implements IComment {

    private CommentRepository commentRepository;
    @Override
    public Comment add(Comment a) {
        return commentRepository.save(a);
    }

    @Override
    public Comment edit(Comment a) {
        return commentRepository.save(a);
    }

    @Override
    public List<Comment> selectAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment SelectById(int id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }
}

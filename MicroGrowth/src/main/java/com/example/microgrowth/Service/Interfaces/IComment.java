package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.DAO.Entities.User;

import java.util.List;

public interface IComment {
    Comment add(Comment a);
    Comment edit(Comment a);
    List<Comment> selectAll();
    Comment SelectById(int id);
    void deleteById(int id);
    List<Comment> getCommentBypublicationid(int id);
    void affecterPubToComment(int publication,int comment);
}

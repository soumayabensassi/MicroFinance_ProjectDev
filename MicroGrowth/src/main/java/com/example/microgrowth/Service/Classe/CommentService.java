package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.DAO.Repositories.CommentRepository;
import com.example.microgrowth.DAO.Repositories.PublicationRepository;
import com.example.microgrowth.Service.Interfaces.IComment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CommentService implements IComment {

    private CommentRepository commentRepository;
    private PublicationRepository publicationRepository;
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

    @Override
    public List<Comment> getCommentBypublicationid(int id) {
        return commentRepository.findByPublicationsIdPublication(id);
    }

    @Override
    public void affecterPubToComment(int publication, int comment) {
        Comment c= commentRepository.findById(comment).get();
        Publication p= publicationRepository.findById(publication).get();
        c.setPublications(p);
        commentRepository.save(c);
    }
}

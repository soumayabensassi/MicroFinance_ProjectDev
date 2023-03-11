package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Interfaces.IComment;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentRestControllers {
    private IComment iComment;
    @GetMapping("/user/afficherComment")
    public List<Comment> afficher()
    {
        return iComment.selectAll();
    }
    @PostMapping("/user/ajouterComment")

    public Comment ajouter(@RequestBody Comment comment)
    {
        return iComment.add(comment);
    }
    @PutMapping("/user/updateComment")
    public Comment update(@RequestBody Comment comment)
    {return iComment.edit(comment);
    }
    @GetMapping("/AfficherCommentbyID/{id}")
    public Comment AfficherByID(@PathVariable int id)
    {
        return iComment.SelectById(id);
    }
    @DeleteMapping("/user/deleteComment/{id}")
    public void delete(@PathVariable int id)
    {
        iComment.deleteById(id);
    }
}

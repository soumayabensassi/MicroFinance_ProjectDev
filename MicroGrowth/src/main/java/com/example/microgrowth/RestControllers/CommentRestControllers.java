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
    @GetMapping("/afficherComment")
    public List<Comment> afficher()
    {
        return iComment.selectAll();
    }
    @PostMapping("/ajouterComment")

    public Comment ajouter(@RequestBody Comment comment)
    {
        return iComment.add(comment);
    }
    @PutMapping("/updateComment")
    public Comment update(@RequestBody Comment comment)
    {return iComment.edit(comment);
    }
    @GetMapping("/ajouterCommentbyID/{id}")
    public Comment AfficherByID(@PathVariable int id)
    {
        return iComment.SelectById(id);
    }
    @DeleteMapping("/deleteComment/{id}")
    public void delete(@PathVariable int id)
    {
        iComment.deleteById(id);
    }
}

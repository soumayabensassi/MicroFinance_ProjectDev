package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Interfaces.IComment;
import com.example.microgrowth.Service.Interfaces.IMicroGrowth;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class CommentRestControllers {
    private IComment iComment;
    private IUser iUser;
    private IMicroGrowth iMicroGrowth;
    @GetMapping("/user/afficherComment")
    public List<Comment> afficher()
    {
        return iComment.selectAll();
    }

    @PostMapping("/user/ajouterComment")
    public String ajouter(@RequestBody Comment comment)
    {
        List<String> motsARechercher = Arrays.asList("mot1", "mot2", "mot3");
        String texte = comment.getText();
        Boolean test=false;
        for (String mot : motsARechercher) {
            if (texte.contains(mot)) {
                texte = texte.replaceAll(mot, "*");
            }
        }
            comment.setUsers(iUser.getUserByEmail(iMicroGrowth.getCurrentUserName()));
            comment.setText(texte);
           // comment.setUsers();
            iComment.add(comment);
            return "ajout done";

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

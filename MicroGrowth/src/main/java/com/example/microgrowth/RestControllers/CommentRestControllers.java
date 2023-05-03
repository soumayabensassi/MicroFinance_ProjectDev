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

    @PostMapping("/user/ajouterComment/{id}/{email}")
    public Comment ajouter(@RequestBody Comment comment,@PathVariable int id,@PathVariable String email)
    {
        List<String> motsARechercher = Arrays.asList("mot1", "mot2", "mot3");
        String texte = comment.getText();
        Boolean test=false;
        for (String mot : motsARechercher) {
            if (texte.contains(mot)) {
                texte = texte.replaceAll(mot, "*");
            }
        }
            comment.setUsers(iUser.getUserByEmail(email));
            comment.setText(texte);
           // comment.setUsers();
            iComment.add(comment);
            iComment.affecterPubToComment(id,comment.getIdComment());
            return comment;

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
    @GetMapping("/AfficherCommentbyPUBID/{id}")
    public List<Comment> AfficherCommentbyPUBID(@PathVariable int id)
    {
        return iComment.getCommentBypublicationid(id);
    }
    @DeleteMapping("/user/deleteComment/{id}")
    public void delete(@PathVariable int id)
    {
        iComment.deleteById(id);
    }
    @PostMapping("/affecterPubToComment/{idComment}/{idPublication}")
    public  void affectation(@PathVariable int idComment,@PathVariable int idPublication)
    {
       iComment.affecterPubToComment(idPublication,idComment);
    }
}

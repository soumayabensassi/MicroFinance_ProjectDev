package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.Service.Interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DislikeRestControllers {
    private IDislike iDislike;
    private ILike iLike;
    private IUser iUser;
    private IPublication iPublication;
    private IComment iComment;

//    @PostMapping("/ajouterSidLike")
//
//    public Dislike ajouter(@RequestBody Dislike dislike)
//    {
//        return iDislike.add(dislike);
//    }
//    @DeleteMapping("/deleteDisLike/{id}")
//    public void delete(@PathVariable int id)
//    {
//        iDislike.deleteById(id);
//    }
public String getCurrentUserName() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
        return null;
    }
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
        return ((UserDetails) principal).getUsername();
    } else {
        return principal.toString();
    }
}
    @PostMapping("/user/DislikerPublication/{idpublication}")
    public void Dislikerunepublication(@PathVariable int idpublication)
    { String email=this.getCurrentUserName();
        Likes likes=iLike.verifLikePublication(email,idpublication);
        Dislike dislike=iDislike.verifDislikePublication(email,idpublication);
        if (likes==null && dislike == null) {
            Dislike d=new Dislike(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iDislike.add(d);
        } else if (likes!=null && dislike==null) {
            iLike.deleteById(likes.getIdLike());
            Dislike d=new Dislike(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iDislike.add(d);
        } else  iDislike.deleteById(dislike.getIdDislike());
    }
    @PostMapping("/user/DislikerComment/{idComment}")
    public void DislikerunCommant(@PathVariable int idComment)
    { String email=this.getCurrentUserName();
        Likes likesComment=iLike.verifLikeComment(email,idComment);
        Dislike dislikeComment=iDislike.verifDislikeComment(email,idComment);
        if (likesComment==null && dislikeComment == null) {
            Dislike d=new Dislike(iUser.getUserByEmail(email),iComment.SelectById(idComment));
            iDislike.add(d);
        } else if (likesComment!=null && dislikeComment==null) {
            iLike.deleteById(likesComment.getIdLike());
            Dislike d=new Dislike(iUser.getUserByEmail(email),iComment.SelectById(idComment));
            iDislike.add(d);
        } else  iDislike.deleteById(dislikeComment.getIdDislike());
    }
}

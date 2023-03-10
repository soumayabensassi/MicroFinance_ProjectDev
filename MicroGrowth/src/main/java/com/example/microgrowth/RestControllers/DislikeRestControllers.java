package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.Service.Interfaces.*;
import lombok.AllArgsConstructor;
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
    @PostMapping("/DislikerPublication/{email}/{idpublication}")
    public void Dislikerunepublication(@PathVariable String email,@PathVariable int idpublication)
    {
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
    @PostMapping("/DislikerComment/{email}/{idComment}")
    public void DislikerunCommant(@PathVariable String email,@PathVariable int idComment)
    {
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

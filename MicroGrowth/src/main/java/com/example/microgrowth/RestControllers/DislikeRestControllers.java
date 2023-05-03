package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.Service.Interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class DislikeRestControllers {
    private IDislike iDislike;
    private ILike iLike;
    private IUser iUser;
    private IPublication iPublication;
    private IComment iComment;
    private IMicroGrowth iMicroGrowth;
    @PostMapping("/user/DislikerPublication/{idpublication}/{email}")
    public void Dislikerunepublication(@PathVariable int idpublication,@PathVariable String email)
    { //String email=iMicroGrowth.getCurrentUserName();
        Likes likes=iLike.verifLikePublication(email,idpublication);
        Dislike dislike=iDislike.verifDislikePublication(email,idpublication);
        Publication p=iPublication.SelectById(idpublication);
        if (likes==null && dislike == null) {
            Dislike d=new Dislike(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iDislike.add(d);
            p.setNombreDislike(iDislike.totalDisLike(idpublication));
            iPublication.edit(p);
        } else if (likes!=null && dislike==null) {
            iLike.deleteById(likes.getIdLike());
            Dislike d=new Dislike(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iDislike.add(d);
            p.setNombreDislike(iDislike.totalDisLike(idpublication));
            p.setNombreLike(iLike.totalLike(idpublication));
            iPublication.edit(p);
        } else
        {iDislike.deleteById(dislike.getIdDislike());
            p.setNombreDislike(iDislike.totalDisLike(idpublication));
            p.setNombreLike(iLike.totalLike(idpublication));
            iPublication.edit(p);
        }
    }
    @PostMapping("/user/DislikerComment/{idComment}/{email}")
    public void DislikerunCommant(@PathVariable int idComment,@PathVariable String email)
    {   Likes likesComment=iLike.verifLikeComment(email,idComment);
        Dislike dislikeComment=iDislike.verifDislikeComment(email,idComment);
        Comment p=iComment.SelectById(idComment);
        if (likesComment==null && dislikeComment == null) {
            Dislike d=new Dislike(iUser.getUserByEmail(email),iComment.SelectById(idComment));
            iDislike.add(d);
            p.setNombreDislikeComment(iDislike.totalDisLikeComment(idComment));
            iComment.edit(p);
        } else if (likesComment!=null && dislikeComment==null) {
            iLike.deleteById(likesComment.getIdLike());
            Dislike d=new Dislike(iUser.getUserByEmail(email),iComment.SelectById(idComment));
            iDislike.add(d);
            p.setNombreDislikeComment(iDislike.totalDisLikeComment(idComment));
            p.setNombreLikeComment(iLike.totalLikeComment(idComment));
            iComment.edit(p);
        } else  {
            iDislike.deleteById(dislikeComment.getIdDislike());
            p.setNombreDislikeComment(iDislike.totalDisLikeComment(idComment));
            p.setNombreLikeComment(iLike.totalLikeComment(idComment));
            iComment.edit(p);
    }}
    @GetMapping("totalDisLike/{id}")
    public int  getTotalDislike(@PathVariable int id)
    {
        return iDislike.totalDisLike(id);
    }
}

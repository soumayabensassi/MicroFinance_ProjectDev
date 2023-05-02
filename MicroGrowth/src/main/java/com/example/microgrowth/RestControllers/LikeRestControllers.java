package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.Service.Interfaces.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class LikeRestControllers {
    private IUser iUser;
    private ILike iLike;
    private IDislike iDislike;
    private IPublication iPublication;
    private IComment iComment;
    private IMicroGrowth iMicroGrowth;



    @PostMapping("/user/LikerPublication/{idpublication}/{email}")
    public void likerunepublication(@PathVariable int idpublication,@PathVariable String email)
    {
//    String email=iMicroGrowth.getCurrentUserName();
//        System.out.println("email:"+email);
        Likes likes=iLike.verifLikePublication(email,idpublication);
        Dislike dislike=iDislike.verifDislikePublication(email,idpublication);
        Publication p=iPublication.SelectById(idpublication);
        log.info("likes is : {}",likes);
        if (likes==null && dislike == null) {
            Likes like=new Likes(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iLike.add(like);
            p.setNombreLike(iLike.totalLike(idpublication));
            iPublication.edit(p);
        } else if (likes==null && dislike!=null) {
            iDislike.deleteById(dislike.getIdDislike());
            Likes like=new Likes(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iLike.add(like);
            p.setNombreLike(iLike.totalLike(idpublication));
            p.setNombreDislike(iDislike.totalDisLike(idpublication));
            iPublication.edit(p);
        } else
        {iLike.deleteById(likes.getIdLike());
            p.setNombreLike(iLike.totalLike(idpublication));
            p.setNombreDislike(iDislike.totalDisLike(idpublication));
            iPublication.edit(p);
        }
    }
    @PostMapping("/user/LikerComment/{idComment}/{email}")
    public void likerunComment(@PathVariable int idComment,@PathVariable String email)
    {//String email=iMicroGrowth.getCurrentUserName();
        Likes likesComment=iLike.verifLikeComment(email,idComment);
        Dislike dislikeComment=iDislike.verifDislikeComment(email,idComment);
        Comment p=iComment.SelectById(idComment);
        if (likesComment==null && dislikeComment == null) {
            Likes like=new Likes(iUser.getUserByEmail(email),iComment.SelectById(idComment));
            iLike.add(like);
            p.setNombreLikeComment(iLike.totalLikeComment(idComment));
            iComment.edit(p);
        } else if (likesComment==null && dislikeComment!=null) {
            iDislike.deleteById(dislikeComment.getIdDislike());
            Likes like=new Likes(iUser.getUserByEmail(email),iComment.SelectById(idComment));
            iLike.add(like);
            p.setNombreDislikeComment(iDislike.totalDisLikeComment(idComment));
            p.setNombreLikeComment(iLike.totalLikeComment(idComment));
            iComment.edit(p);
        } else  {iLike.deleteById(likesComment.getIdLike());
            p.setNombreDislikeComment(iDislike.totalDisLikeComment(idComment));
            p.setNombreLikeComment(iLike.totalLikeComment(idComment));
            iComment.edit(p);
        }}
 @GetMapping("totalLike/{id}")
 public int  getTotallike(@PathVariable int id)
 {
     return iLike.totalLike(id);
 }
}

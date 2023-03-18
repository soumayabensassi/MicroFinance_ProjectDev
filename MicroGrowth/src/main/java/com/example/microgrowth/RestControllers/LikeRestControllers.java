package com.example.microgrowth.RestControllers;

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
public class LikeRestControllers {
    private IUser iUser;
    private ILike iLike;
    private IDislike iDislike;
    private IPublication iPublication;
    private IComment iComment;
    private IMicroGrowth iMicroGrowth;



    @PostMapping("/user/LikerPublication/{idpublication}")
    public void likerunepublication(@PathVariable int idpublication)
    {   String email=iMicroGrowth.getCurrentUserName();
        System.out.println(email);
        Likes likes=iLike.verifLikePublication(email,idpublication);
        Dislike dislike=iDislike.verifDislikePublication(email,idpublication);
        log.info("likes is : {}",likes);
        if (likes==null && dislike == null) {
            Likes like=new Likes(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iLike.add(like);
        } else if (likes==null && dislike!=null) {
            iDislike.deleteById(dislike.getIdDislike());
            Likes like=new Likes(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iLike.add(like);
        } else  iLike.deleteById(likes.getIdLike());
    }
    @PostMapping("/user/LikerComment/{idComment}")
    public void likerunComment(@PathVariable int idComment)
    {String email=iMicroGrowth.getCurrentUserName();
        Likes likesComment=iLike.verifLikeComment(email,idComment);
        Dislike dislikeComment=iDislike.verifDislikeComment(email,idComment);
        if (likesComment==null && dislikeComment == null) {
            Likes like=new Likes(iUser.getUserByEmail(email),iComment.SelectById(idComment));
            iLike.add(like);
        } else if (likesComment==null && dislikeComment!=null) {
            iDislike.deleteById(dislikeComment.getIdDislike());
            Likes like=new Likes(iUser.getUserByEmail(email),iComment.SelectById(idComment));
            iLike.add(like);
        } else  iLike.deleteById(likesComment.getIdLike());
    }

}

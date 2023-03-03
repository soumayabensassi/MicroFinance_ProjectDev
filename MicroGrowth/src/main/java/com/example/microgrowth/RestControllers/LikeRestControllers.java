package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.Service.Interfaces.IDislike;
import com.example.microgrowth.Service.Interfaces.ILike;
import com.example.microgrowth.Service.Interfaces.IPublication;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class LikeRestControllers {
    private IUser iUser;
    private ILike iLike;
    private IDislike iDislike;
    private IPublication iPublication;
//    @PostMapping("/ajouterLike")
//
//    public Likes ajouter(@RequestBody Likes like)
//    {
//        return iLike.add(like);
//    }
    @PostMapping("/Liker/{email}/{idpublication}")
    public void likerunepublication(@PathVariable String email,@PathVariable int idpublication)
    {
        Likes likes=iLike.verifLike(email,idpublication);
        Dislike dislike=iDislike.verifDislike(email,idpublication);
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


}

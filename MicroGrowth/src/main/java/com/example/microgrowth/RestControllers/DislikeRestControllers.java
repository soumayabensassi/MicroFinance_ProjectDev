package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.Service.Interfaces.IDislike;
import com.example.microgrowth.Service.Interfaces.ILike;
import com.example.microgrowth.Service.Interfaces.IPublication;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DislikeRestControllers {
    private IDislike iDislike;
    private ILike iLike;
    private IUser iUser;
    private IPublication iPublication;

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
    @PostMapping("/Disliker/{email}/{idpublication}")
    public void Dislikerunepublication(@PathVariable String email,@PathVariable int idpublication)
    {
        Likes likes=iLike.verifLike(email,idpublication);
        Dislike dislike=iDislike.verifDislike(email,idpublication);
        if (likes==null && dislike == null) {
            Dislike d=new Dislike(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iDislike.add(d);
        } else if (likes!=null && dislike==null) {
            iLike.deleteById(likes.getIdLike());
            Dislike d=new Dislike(iUser.getUserByEmail(email),iPublication.SelectById(idpublication));
            iDislike.add(d);
        } else  iDislike.deleteById(dislike.getIdDislike());
    }
}

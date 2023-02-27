package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.Service.Interfaces.ILike;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class LikeRestControllers {
    private ILike iLike;
    @PostMapping("/ajouterLike")

    public Likes ajouter(@RequestBody Likes like)
    {
        return iLike.add(like);
    }
    @DeleteMapping("/deleteLike/{id}")
    public void delete(@PathVariable int id)
    {
        iLike.deleteById(id);
    }
}

package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.Service.Interfaces.IDislike;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DislikeRestControllers {
    private IDislike iDislike;
    @PostMapping("/ajouterSidLike")

    public Dislike ajouter(@RequestBody Dislike dislike)
    {
        return iDislike.add(dislike);
    }
    @DeleteMapping("/deleteDisLike/{id}")
    public void delete(@PathVariable int id)
    {
        iDislike.deleteById(id);
    }
}

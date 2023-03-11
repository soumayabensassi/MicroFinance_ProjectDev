package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.Service.Interfaces.IComment;
import com.example.microgrowth.Service.Interfaces.IPublication;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PublicationRestControllers {
    private IPublication iPublication;
    @GetMapping("/admin/afficherPublication")
    public List<Publication> afficher()
    {
        return iPublication.selectAll();
    }
    @PostMapping("/user/ajouterPublication")

    public Publication ajouter(@RequestBody Publication publication)
    {
        return iPublication.add(publication);
    }
    @PutMapping("/user//updatePublication")
    public Publication update(@RequestBody Publication publication)
    {return iPublication.edit(publication);
    }
    @GetMapping("/afficherPublicationbyID/{id}")
    public Publication AfficherByID(@PathVariable int id)
    {
        return iPublication.SelectById(id);
    }
    @DeleteMapping("/admin/deletePublication/{id}")
    public void delete(@PathVariable int id)
    {
        iPublication.deleteById(id);
    }
}

package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.Service.Interfaces.IComment;
import com.example.microgrowth.Service.Interfaces.IPublication;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    public String ajouter(@RequestBody Publication publication)
    {List<String> motsARechercher = Arrays.asList("mot1", "mot2", "mot3");
        String texte = publication.getText();
        Boolean test=false;
        for (String mot : motsARechercher) {
            if (texte.contains(mot)) {
                test=true;
            }
            }
        if (test) {
            return "la publication contient des mots non approprié";
        }
        else {
            iPublication.add(publication);
            return "ajout done";
        }
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
    @PostMapping("/admin/aprouvePublication/{id}")
    public void AprouvePublication(@PathVariable int id)
    {
        Publication publication=iPublication.SelectById(id);
        publication.setState(true);
        iPublication.edit(publication);
    }
    @PostMapping("/admin/AfficheraprouvePublication")
    public List<Publication> AprouvePublication()
    {

       return iPublication.SelectPublicationAprouve();
    }
}

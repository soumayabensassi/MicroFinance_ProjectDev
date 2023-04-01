package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Comment;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.Service.Interfaces.IComment;
import com.example.microgrowth.Service.Interfaces.IMicroGrowth;
import com.example.microgrowth.Service.Interfaces.IPublication;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class PublicationRestControllers {
    private IPublication iPublication;
    private IUser iUser;
    private IMicroGrowth iMicroGrowth;
    @GetMapping("afficherPublication")
    public List<Publication> afficher()
    {
        return iPublication.selectAll();
    }

    @PostMapping("/user/ajouterPublication/{email}")

    public String ajouter(@RequestBody Publication publication,@PathVariable String email)
    {List<String> motsARechercher = Arrays.asList("mot1", "mot2", "mot3");
        String texte = publication.getText();
        for (String mot : motsARechercher) {
            if (texte.contains(mot)) {
                texte = texte.replaceAll(mot, "*");
            }
            }
        publication.setUsers(iUser.getUserByEmail(email));

        publication.setText(texte);
            iPublication.add(publication);
            return "ajout done";


    }
    @PutMapping("/user/updatePublication")
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

package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.Projet;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import com.example.microgrowth.Service.Interfaces.IProjet;
import com.example.microgrowth.Service.Classe.InvestmentService;
import com.example.microgrowth.Service.Classe.ProjetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import com.example.microgrowth.Service.Interfaces.IProjet;
@RestController
@AllArgsConstructor
public class ProjetRestController {

   private IProjet iProjet;
   private IInvestment IInvestment;

    @GetMapping("/admin/afficherProjet")
    public List<Projet> afficher (){

        return iProjet.selectAll();
    }

    @PostMapping("/admin/ajouterProjet")
    public Projet ajouter(@RequestBody  Projet projet) {

        return iProjet.add(projet);
    }

    @DeleteMapping("/admin/deleteProjetbyID/{id}")
    public void delete(@PathVariable Long id)
    {
        iProjet.deleteById(id);
    }

    @PutMapping("/admin/modifierProjet/{id}")
    public Projet modif(@RequestBody  Projet projet){

        return iProjet.modif(projet);
    }
    @PostMapping("/admin/{projetId}/calculerRendementAnnuel/{investissementId}")
    public Double calculerRendementAnnuel(@PathVariable Long projetId, @PathVariable int investissementId,
            @RequestParam("investissementInitial") double investissementInitial , @RequestParam("tauxRendement") double tauxRendement
    ) {
        Projet projet = iProjet.selectById(projetId);
        Investment investissement = projet.getInvestments().stream().filter(i -> i.getIdInvestment()==(investissementId)).findFirst().get();
        double rendementAnnuel = iProjet.calculerRendementAnnuel(investissement.getAmountInves(), projet.getTauxRendement());
        return rendementAnnuel;
    }
    @PostMapping("/admin/restObligation/{projetId}")
    public void recalculerObligation(@PathVariable Long projetId,@RequestParam("investment") double investment){
        iProjet.recalculerObligation(projetId,investment);
    }

}

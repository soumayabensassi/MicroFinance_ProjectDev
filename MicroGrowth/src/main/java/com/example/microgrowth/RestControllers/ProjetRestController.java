package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.Projet;
import com.example.microgrowth.DAO.Repositories.ProjetRepository;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import com.example.microgrowth.Service.Interfaces.IProjet;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProjetRestController {

   private IProjet iProjet;
   private IInvestment IInvestment;
    ProjetRepository projetRepository;

    @GetMapping("/afficherProjet")
    public List<Projet> afficher (){

        return iProjet.selectAll();
    }

    @PostMapping("/ajouterProjet")
    public void ajouter(@RequestBody  Projet projet) {

         iProjet.add(projet);
    }
    @GetMapping("/afficherProjectbyID/{id}")
    public Projet AfficherByID(@PathVariable Long id)
    {
        return iProjet.selectById(id);
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
    @PostMapping("/admin/calculerRendementAnnuel/{investissementId}/{projetId}")
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
    @PostMapping("/stockInterest")
    public Double calculateAnnualReturn(
            @RequestParam("purchasePrice") Double purchasePrice,
            @RequestParam("currentPrice") Double currentPrice,
            @RequestParam("dividendYield") Double dividendYield,
            @RequestParam("numShares") Integer numShares,
            @RequestParam("holdingPeriod") Integer holdingPeriod) {
        double annualReturn = iProjet.calculerIntrestStocks(purchasePrice, currentPrice, numShares, dividendYield,  holdingPeriod);
        return annualReturn;
    }




}

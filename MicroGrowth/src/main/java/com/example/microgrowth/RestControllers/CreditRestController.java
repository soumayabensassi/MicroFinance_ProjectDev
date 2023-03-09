package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.Service.Interfaces.ICredit;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CreditRestController {
    private ICredit iCredit;
    CreditRepository creditRepository;
    @GetMapping("/afficherCredits")
    public List<Credit> afficherCredits()
    {
        return iCredit.selectAll();
    }
@PostMapping("/ajouterCreditByuser")
    public Credit ajouterCredit_user(@RequestBody Credit credit)
    {
        return iCredit.add_credit_user(credit);
    }
    @PostMapping("/ajouterCreditByadmin")
    public Credit ajouterCredit_admin(@RequestBody Credit credit)
    {
        return iCredit.add_credit_admin(credit);
    }
    @PutMapping("/updateCredit")
    public Credit updateCredit(@RequestBody Credit credit)
    {return iCredit.edit(credit);}
    @GetMapping("/afficherCreditbyID/{id}")
    public Credit AfficherCreditByID(@PathVariable int id)
    {
        return iCredit.SelectById(id);
    }
    @DeleteMapping("/deleteCredit/{id}")
    public void delete(@PathVariable int id)
    {
        iCredit.deleteById(id);
    }

    @GetMapping("/AfficherScoreCredit/{id}")
    public int afficherScore(@PathVariable int id)
    {
        return iCredit.scoreCredit(id);
    }
   // @GetMapping("/afficherScoreCredit/{idUser}/{idCredit}/{idCompte}")
    //public int afficherscore(@PathVariable int idUser, @PathVariable int idCredit,@PathVariable int idCompte){
//return iCredit.afficherScore()    }

    @GetMapping("/afficherTableauCredit/{id}")
    public void calcul_tableau_credit(@PathVariable int id){
        iCredit.calcul_tableau_credit(creditRepository.findById(id).orElse(null));
    }
}

package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.Penalite;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.Service.Interfaces.ICredit;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class CreditRestController {
    private ICredit iCredit;
    CreditRepository creditRepository;
    IInvestment iInvestment;
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

    @GetMapping("/admin/CalculRentabilité")
    public double calcul_Rentabilté_desCredits()
    {   //Rentabilité financière = (résultat d'exploitation — impôts sur les bénéfices —
        // versés aux dettes financières) / capitaux propres

        double resultat=0;
        List<Credit> creditsList =iCredit.selectAll();
        for (Credit c : creditsList)
        {
            resultat+=iCredit.calcul_Rentabilite_parCredit(c);
        }
        //En Tunisie, la réglementation bancaire exige que le capital minimum d'une micro banque soit de 2 millions de dinars tunisiens
        // (environ 700 000 euros) pour obtenir une licence bancaire.
        // Cependant, le capital réel nécessaire dépendra des besoins spécifiques de la micro banque.
        double Rentabilte=(resultat-(resultat*.25))/2000000;
        System.out.println("resultat ="+resultat);
        System.out.println("Rentabilte ="+Rentabilte);
        return  Rentabilte;
    }
    @GetMapping("/admin/CalculRentabilitéNonActualisé")
    public double calcul_Rentabilté_desCreditsNonActualisé()
    {   //Rentabilité financière = (résultat d'exploitation — impôts sur les bénéfices(25% des Résultat)  —
        // versés aux dettes financières) / capitaux propres

        double resultat=0;
        List<Credit> creditsList =iCredit.selectAll();
        for (Credit c : creditsList)
        {
            resultat+=iCredit.calcul_Rentabilite_parCreditNonActialise(c);
        }
        //En Tunisie, la réglementation bancaire exige que le capital minimum d'une micro banque soit de 2 millions de dinars tunisiens
        // (environ 700 000 euros) pour obtenir une licence bancaire.
        // Cependant, le capital réel nécessaire dépendra des besoins spécifiques de la micro banque.
        double Rentabilte=(resultat-(resultat*.25))/2000000;
        System.out.println("resultat ="+resultat);
        System.out.println("Rentabilte ="+Rentabilte);
        return  Rentabilte;
    }
    @GetMapping("/admin/CalculNetInterestMarge")
    public double calculNet_Interest_Marge() {

        return iCredit.calcul_Net_Interest_Marge();
    }

    @GetMapping("/admin/TauxDeRemboursement")
    @Scheduled(cron = "0 0 10 31 12 ?")
    public double calculTauxDeRemboursement() {
        System.out.println(iCredit.TauxRemboursement());
        return iCredit.TauxRemboursement();
    }
    @GetMapping("/admin/TauxDeDefaut")
    @Scheduled(cron = "0 0 10 31 12 ?")
    public double calculTauxDeDefaut() {

        return iCredit.TauxDeDefaut();
    }
   @GetMapping("/admin/ReturnOnEquity")
   @Scheduled(cron = "0 0 10 31 12 ?")
   public  double CalculROE()
   {
       double resultatNet=iCredit.CalculResultatNET();
       return  resultatNet/2000000;

   }
    @GetMapping("/admin/ReturnOnAssets")
    @Scheduled(cron = "0 0 10 31 12 ?")
    public  double CalculROA() {
        double resultatNet = iCredit.CalculResultatNET();
        double ActifNet = iCredit.CalculActifCredit() + iCredit.CalculActifRéserve();
        return resultatNet / ActifNet;
    }
    @GetMapping("/afficherDiffDate/{id}")
    public Credit afficherDifDate(@PathVariable int id)
    {
        iCredit.ajouter_date_30(creditRepository.findById(id).orElse(null));
        return null;
    }
    @GetMapping("/creditremboursee")
    public List<Credit> afficherCreditRem(){
        return creditRepository.selectCreditRembourseeParMois();
    }
    @PostMapping("/accordePenalite")
    public  void Accorde_penalite(@RequestBody Penalite p){
        iCredit.Accorde_penalite(p);
    }
}
